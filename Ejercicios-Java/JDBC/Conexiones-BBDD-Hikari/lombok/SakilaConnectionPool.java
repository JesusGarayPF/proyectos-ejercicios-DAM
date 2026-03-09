package tema_02.lombok;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class SakilaConnectionPool {


    // Instancia Singleton del pool de conexiones
    private static SakilaConnectionPool instance = null;

    // Pool de conexiones de HikariCP
    private HikariDataSource dataSource;

    // Constructor privado para Singleton
    private SakilaConnectionPool() {
        // Configuración del pool de conexiones
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mariadb://localhost/sakila");
        config.setUsername("sakilauser");
        config.setPassword("pwdsakilauser");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);

        // Inicializar el data source
        this.dataSource = new HikariDataSource(config);
    }

    // Método para obtener la instancia Singleton
    public static SakilaConnectionPool getInstance() {
        if (instance == null) {
            synchronized (SakilaConnectionPool.class) {
                if (instance == null) {
                    instance = new SakilaConnectionPool();
                }
            }
        }
        return instance;
    }

    // Método para obtener una conexión del pool
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Método para cerrar el pool de conexiones
    public void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}
