import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Tarea05 {
    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";
    private static final String SQL_UPDATE_01 = "update language set name = left (name, length(name)-1) where name like '%X'";

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
