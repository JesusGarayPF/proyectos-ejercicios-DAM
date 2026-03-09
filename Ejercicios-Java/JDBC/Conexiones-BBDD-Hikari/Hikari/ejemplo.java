package tema_02.Hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ejemplo {
    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";

    private static final String UPDATE_INUTIL = "update customer set first_name = ? where customer_id = 10000";

    public static void main(String[] args) {
        HikariConfig connectionPoolConfig = new HikariConfig();
        connectionPoolConfig.setJdbcUrl(CADENA_CONEXION);
        connectionPoolConfig.setUsername(USUARIO);
        connectionPoolConfig.setPassword(PASSWORD);
        connectionPoolConfig.setMaximumPoolSize(2);
        try (HikariDataSource dataSource = new HikariDataSource(connectionPoolConfig)) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement ps = connection.prepareStatement(UPDATE_INUTIL)) {
                ps.setString(1, "Esto no se llega a actualizar con el update anterior");
                int numregistros = ps.executeUpdate();
                System.out.printf("Se han actualizado %d registros \n", numregistros);
                System.out.println("El tipo de la conexión obtenida con el pool es:");
                System.out.println(connection.getClass().getSimpleName());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
