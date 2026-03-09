package dataaccess;

import entities.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz {@link CustomerDataAccess} para manejar las operaciones de base de datos relacionadas con los clientes.
 * Esta clase utiliza consultas SQL para interactuar con la tabla `customers`.
 * Se apoya en un {@link HikariConnectionPool} para las conexiones a la base de datos.
 */
public class CustomerDataAccessImpl implements CustomerDataAccess {

    /**
     * Consulta SQL para obtener todos los clientes de la base de datos.
     */
    private final static String QUERY_FIND_ALL = "select * from customers";

    /**
     * Consulta SQL para encontrar un cliente por su número de cliente.
     */
    private final static String QUERY_BY_ID = "select * from customers where customerNumber=?";

    /**
     * Consulta SQL para contar el número total de clientes.
     */
    private final static String QUERY_COUNT = "select count(*) from customers";

    /**
     * Consulta SQL para actualizar un cliente existente.
     */
    private final static String UPDATE = "update customers set customerNumber=?, customerName=?, contactLastName=?, contactFirstName=?, phone=?, addressLine1=?, addressLine2=?, city=?, state=?, postalCode=?, country=?, salesRepEmployeeNumber=?, creditLimit=? where customerNumber=?";

    /**
     * Consulta SQL para insertar un nuevo cliente en la base de datos.
     */
    private final static String INSERT = "insert into customers(customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

    /**
     * Consulta SQL para eliminar un cliente de la base de datos por su número de cliente.
     */
    private final static String DELETE = "delete from customers where customerNumber=?";

    /**
     * Pool de conexiones utilizado para manejar las conexiones a la base de datos.
     */
    private final HikariConnectionPool connectionPool;

    /**
     * Construye una nueva instancia de {@code CustomerDataAccessImpl} e inicializa el pool de conexiones.
     *
     * @throws SQLException si ocurre un error al establecer la conexión con la base de datos.
     */
    public CustomerDataAccessImpl() throws SQLException {
        this.connectionPool = HikariConnectionPool.getInstance();
    }

    /**
     * Cuenta el número total de clientes en la base de datos.
     *
     * @return el número total de clientes.
     * @throws SQLException si ocurre un error de acceso a la base de datos.
     */
    @Override
    public long count() throws SQLException {
        long count = 0;
        try (Statement statement = connectionPool.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY_COUNT)) {
            while (resultSet.next()) {
                count = resultSet.getLong(1);
            }
        }
        return count;
    }

    /**
     * Verifica si un cliente existe por su número de cliente.
     *
     * @param id el número de cliente.
     * @return {@code true} si el cliente existe, {@code false} en caso contrario.
     * @throws SQLException si ocurre un error de acceso a la base de datos.
     */
    @Override
    public boolean existsById(int id) throws SQLException {
        try (PreparedStatement ps = connectionPool.getConnection().prepareStatement(QUERY_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    /**
     * Busca un cliente por su número de cliente.
     *
     * @param id el número de cliente.
     * @return un {@code Optional<Customer>} que contiene el cliente si se encuentra, o vacío si no.
     * @throws SQLException si ocurre un error de acceso a la base de datos.
     */
    @Override
    public Optional<Customer> findById(int id) throws SQLException {
        try (PreparedStatement ps = connectionPool.getConnection().prepareStatement(QUERY_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Customer(
                            rs.getInt("customerNumber"),
                            rs.getString("customerName"),
                            rs.getNString("contactLastName"),
                            rs.getNString("contactFirstName"),
                            rs.getNString("phone"),
                            rs.getNString("addressLine1"),
                            rs.getNString("addressLine2"),
                            rs.getNString("city"),
                            rs.getNString("state"),
                            rs.getNString("postalCode"),
                            rs.getNString("country"),
                            rs.getInt("salesRepEmployeeNumber"),
                            rs.getFloat("creditLimit")
                    ));
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    /**
     * Recupera todos los clientes de la base de datos.
     *
     * @return una lista de objetos {@code Customer}.
     * @throws SQLException si ocurre un error de acceso a la base de datos.
     */
    @Override
    public List<Customer> findAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (Statement statement = connectionPool.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY_FIND_ALL)) {
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("customerNumber"),
                        resultSet.getString("customerName"),
                        resultSet.getNString("contactLastName"),
                        resultSet.getNString("contactFirstName"),
                        resultSet.getNString("phone"),
                        resultSet.getNString("addressLine1"),
                        resultSet.getNString("addressLine2"),
                        resultSet.getNString("city"),
                        resultSet.getNString("state"),
                        resultSet.getNString("postalCode"),
                        resultSet.getNString("country"),
                        resultSet.getInt("salesRepEmployeeNumber"),
                        resultSet.getFloat("creditLimit")
                );
                customers.add(customer);
            }
        }
        return customers;
    }

    /**
     * Guarda un cliente en la base de datos. Si el cliente ya existe, actualiza su información.
     * De lo contrario, inserta un nuevo cliente.
     *
     * @param customer el cliente a guardar.
     * @return el {@code Customer} guardado.
     * @throws SQLException si ocurre un error de acceso a la base de datos.
     */
    @Override
    public Customer save(Customer customer) throws SQLException {
        if (existsById(customer.getCustomerNumber())) {
            try (PreparedStatement ps = connectionPool.getConnection().prepareStatement(UPDATE)) {
                ps.setInt(1, customer.getCustomerNumber());
                ps.setString(2, customer.getCustomerName());
                ps.setString(3, customer.getContactLastName());
                ps.setString(4, customer.getContactFirstName());
                ps.setString(5, customer.getPhone());
                ps.setString(6, customer.getAddressLine1());
                ps.setString(7, customer.getAddressLine2());
                ps.setString(8, customer.getCity());
                ps.setString(9, customer.getState());
                ps.setString(10, customer.getPostalCode());
                ps.setString(11, customer.getCountry());
                ps.setInt(12, customer.getSalesRepEmployeeNumber());
                ps.setFloat(13, customer.getCreditLimit());
                int filasAfectadas = ps.executeUpdate();
                System.out.println("Cliente actualizado. Filas afectadas: " + filasAfectadas );
            }
        } else {
            try (PreparedStatement ps = connectionPool.getConnection().prepareStatement(INSERT)) {
                ps.setInt(1, customer.getCustomerNumber());
                ps.setString(2, customer.getCustomerName());
                ps.setString(3, customer.getContactLastName());
                ps.setString(4, customer.getContactFirstName());
                ps.setString(5, customer.getPhone());
                ps.setString(6, customer.getAddressLine1());
                ps.setString(7, customer.getAddressLine2());
                ps.setString(8, customer.getCity());
                ps.setString(9, customer.getState());
                ps.setString(10, customer.getPostalCode());
                ps.setString(11, customer.getCountry());
                ps.setInt(12, customer.getSalesRepEmployeeNumber());
                ps.setFloat(13, customer.getCreditLimit());
                int filasAfectadas = ps.executeUpdate();
                System.out.println("Cliente creado. Filas afectadas: " + filasAfectadas );
            }
        }
        return customer;
    }

    /**
     * Elimina un cliente de la base de datos por su número de cliente.
     *
     * @param id el número de cliente.
     * @throws SQLException si ocurre un error de acceso a la base de datos.
     */
    @Override
    public void deleteById(int id) throws SQLException {
        if (existsById(id)) {
            try (PreparedStatement ps = connectionPool.getConnection().prepareStatement(DELETE)) {
                ps.setInt(1, id);
                int filasAfectadas = ps.executeUpdate();
                System.out.printf("Se ha borrado el cliente %d\nFilas borradas: %d\n", id, filasAfectadas);
            }
        }
    }
}

