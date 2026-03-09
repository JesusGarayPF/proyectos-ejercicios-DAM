package prueba;

import java.sql.*;
import java.time.LocalDateTime;

public class PrimeraConexion {
    //URL DE CONEXION : "jdbc:tipo_de_servidor://maquina[:puerto]
    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";
    private static final String SQL_QUERY_01 = "select country_id, country, last_update from country";

    public static void main(String[] args) throws SQLException {
        System.out.printf("Son las %s y este es el primer programa en Intellij Idea", LocalDateTime.now());
        try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultados = statement.executeQuery(SQL_QUERY_01)){
            System.out.printf("El tipo dinamico de la conexion es %s\n", connection.getClass().getName());
            System.out.printf("El tipo dinamico de la conexion es %s\n", statement.getClass().getName());
            System.out.printf("El tipo dinamico de la conexion es %s\n", resultados.getClass().getName());

//            if (resultados.isBeforeFirst()){
//                System.out.println("No se han encontrado resultados");
//            }
            int numFila = 1;
            while (resultados.next()) {
                System.out.printf("En registro %d\n", numFila++);
                String countryId = resultados.getString(1);
                String contryName = resultados.getString(3);
                String lastUpdate = resultados.getString(3);
                System.out.printf("Id %s \n", countryId);
                System.out.println("-".repeat(100));
            }
        }
        System.out.println("Fin de programa");
    }
}
