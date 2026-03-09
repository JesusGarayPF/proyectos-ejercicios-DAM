package tema_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejercicio02 {
    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";

    private static final String QUERY = "insert into prueba_transaccion (contenido, fecha_creacion) values (?, now())";


    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String valor = "";
        try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD)) {
            try {
                connection.setAutoCommit(false);
                while (!valor.equalsIgnoreCase("FIN")) {
                    System.out.println("Introduce un valor para el campo \"Contenido\". Introduce FIN para terminar ");
                    valor = sc.next();
                    if (valor.equalsIgnoreCase("FIN")){
                        break;
                    }
                    try (PreparedStatement ps1 = connection.prepareStatement(QUERY)) {
                        ps1.setString(1, valor);
                        int numInsert = ps1.executeUpdate();
                    }
                    connection.commit();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Error:Anulando transaccion");
                connection.rollback();
            }
        }
    }
}
