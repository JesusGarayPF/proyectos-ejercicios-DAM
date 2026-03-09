import java.sql.*;

public class Tarea04 {
    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";
    private static final String SQL_UPDATE_01 = """
            update language
            set name = concat(name, 'X')
             """;

    public static void main(String[] args) throws SQLException {

        try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD);
             Statement statement = connection.createStatement()){
             int numFilasAfectasdas = statement.executeUpdate(SQL_UPDATE_01);
            System.out.println("Se han modificado " + numFilasAfectasdas + " filas");
        }catch (SQLException a){
            System.out.println("No se pudo establecer conexion");
        }
        System.out.println("Fin de programa");
    }
}
