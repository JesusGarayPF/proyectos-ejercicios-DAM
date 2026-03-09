package tema_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejercicio03 {
    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";

    private static final String QUERY = "insert into prueba_transaccion (contenido, fecha_creacion) values (?, now())";


    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Boolean terminar = false;
        try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD)) {
            try {
                connection.setAutoCommit(false);
                while (!terminar) {
                    int contador = 0;
                    String valor = "";
                    while (contador < 10) {
                        System.out.println("Introduce 10 valores para el campo \"Contenido\"");
                        valor = sc.nextLine();
                        try (PreparedStatement ps1 = connection.prepareStatement(QUERY)) {
                            ps1.setString(1, valor);
                            int numInsert = ps1.executeUpdate();
                            contador++;

                        }
                    }
                    System.out.println("Introduce 1 para confirmar la inserción. 0 para anular (" + contador + " valores)");
                    int respuesta = sc.nextInt();
                    if (respuesta == 1) {
                        connection.commit();
                    } else {
                        connection.rollback();
                    }
                    connection.commit();
                    System.out.println("Quiere introducir 10 valores mas? Introduzca 1 para confirmar. 0 para terminar");
                    int confirmacion = sc.nextInt();
                    if (confirmacion == 0){
                        terminar = true;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Error:Anulando transaccion");
                connection.rollback();
            }
        }
    }
}
