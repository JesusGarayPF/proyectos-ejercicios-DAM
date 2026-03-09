package dataaccess;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase que implementa un pool de conexiones utilizando HikariCP.
 * Proporciona un acceso eficiente y seguro a la base de datos.
 */
public class HikariConnectionPool {

    /**
     * Instancia única del pool de conexiones (singleton).
     */
    private static HikariConnectionPool instance;

    /**
     * Fuente de datos para la conexión.
     */
    private HikariDataSource dataSource;

    /**
     * Cadena de conexión a la base de datos.
     */
    private static final String CONNECTION_STRING = "jdbc:mariadb://localhost/classicmodels";

    /**
     * Nombre de usuario para la base de datos.
     */
    private static final String USERNAME = "classicmodels";

    /**
     * Contraseña para la base de datos.
     */
    private static final String PASSWORD = "classicmodels";

    /**
     * Constructor privado para inicializar el pool de conexiones.
     * Configura los parámetros de la conexión y crea la fuente de datos.
     */
    private HikariConnectionPool() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(CONNECTION_STRING);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(15); // Número máximo de conexiones en el pool
        config.setConnectionTimeout(5000); // Tiempo máximo de espera para obtener una conexión
        dataSource = new HikariDataSource(config);
    }

    /**
     * Obtiene la instancia única del pool de conexiones.
     *
     * @return la instancia del HikariConnectionPool.
     */
    public static HikariConnectionPool getInstance() {
        if (instance == null) {
            synchronized (HikariConnectionPool.class) {
                if (instance == null) {
                    instance = new HikariConnectionPool();
                }
            }
        }
        return instance;
    }

    /**
     * Obtiene una conexión del pool de conexiones.
     *
     * @return una conexión a la base de datos.
     * @throws SQLException si ocurre un error al obtener la conexión.
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    /**
     * Cierra el pool de conexiones y libera los recursos
     */
    public void close(){
        if(dataSource!= null){
            dataSource.close();
        }
    }
}
