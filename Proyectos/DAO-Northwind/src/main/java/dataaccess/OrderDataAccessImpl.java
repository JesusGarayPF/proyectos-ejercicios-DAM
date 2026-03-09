package dataaccess;

import dto.CreateOrderDto;
import dto.OrderDetailDto;
import entities.Order;
import entities.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz {@link OrderDataAccess} para manejar las operaciones de base de datos relacionadas con los pedidos.
 * Esta clase proporciona métodos para contar, buscar, crear y eliminar pedidos en la base de datos.
 */
public class OrderDataAccessImpl implements OrderDataAccess {

    /** Pool de conexiones a la base de datos. */
    private HikariConnectionPool hcp;

    /** Acceso a datos de productos. */
    private ProductDataAccess productDataAccess;

    /**
     * Constructor que inicializa el acceso a datos de productos.
     *
     * @param productDataAccess acceso a datos de productos.
     */
    public OrderDataAccessImpl(ProductDataAccess productDataAccess) {
        this.productDataAccess = productDataAccess;
        this.hcp = HikariConnectionPool.getInstance();
    }

    /**
     * Constructor por defecto que inicializa el pool de conexiones.
     */
//    //public OrderDataAccessImpl() {
//        this.hcp = HikariConnectionPool.getInstance();
//    }

    /** Consulta para contar todos los pedidos. */
    private static final String COUNT_ALL = "Select count(*) from orders";

    /** Consulta para encontrar todos los pedidos. */
    private static final String FIND_ALL_QUERY = "Select * from orders";

    /** Consulta para encontrar un pedido por su ID. */
    private static final String BY_ID_QUERY = "Select * from orders where orderNumber = ?";

    /** Consulta para eliminar un pedido por su ID. */
    private static final String DELETE_BY_ID = "Delete from orders where orderNumber = ? ";

    /** Consulta para insertar un nuevo pedido. */
    private static final String INSERT_ORDER = """
            Insert into orders (orderDate, requiredDate, status, comments, customerNumber)
            Values (?, ?, 'In Process', ?, ?)
            """;

    /** Consulta para insertar los detalles del pedido. */
    private static final String INSERT_ORDER_DETAILS = "INSERT INTO orderdetails (productCode, quantityOrdered, priceEach) VALUES (?, ?, ?)";

    /**
     * Cuenta el número total de pedidos en la base de datos.
     *
     * @return el número total de pedidos.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public long count() throws SQLException {
        long count = 0;
        try (Statement stmt = hcp.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(COUNT_ALL)) {
            if (rs.next()) {
                count = rs.getLong(1);
            }
        }
        return count;
    }

    /**
     * Verifica si existe un pedido por su ID.
     *
     * @param id el ID del pedido.
     * @return true si el pedido existe, false en caso contrario.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public boolean existsById(int id) throws SQLException {
        try (PreparedStatement ps = hcp.getConnection().prepareStatement(BY_ID_QUERY)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    /**
     * Busca un pedido por su ID.
     *
     * @param id el ID del pedido.
     * @return un Optional que contiene el pedido si existe, vacío en caso contrario.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public Optional<Order> findById(int id) throws SQLException {
        try (PreparedStatement ps = hcp.getConnection().prepareStatement(BY_ID_QUERY)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Order(
                            rs.getInt("orderNumber"),
                            rs.getDate("orderDate").toLocalDate(),
                            rs.getDate("requiredDate").toLocalDate(),
                            rs.getDate("shippedDate") != null ? rs.getDate("shippedDate").toLocalDate() : null,
                            rs.getString("status"),
                            rs.getString("comments"),
                            rs.getInt("customerNumber")
                    ));
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Recupera todos los pedidos de la base de datos.
     *
     * @return una lista de todos los pedidos.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public List<Order> findAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        try (Statement st = hcp.getConnection().createStatement();
             ResultSet rs = st.executeQuery(FIND_ALL_QUERY)) {
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("orderNumber"),
                        rs.getDate("orderDate").toLocalDate(),
                        rs.getDate("requiredDate").toLocalDate(),
                        rs.getDate("shippedDate") != null ? rs.getDate("shippedDate").toLocalDate() : null,
                        rs.getString("status"),
                        rs.getString("comments"),
                        rs.getInt("customerNumber")
                );
                orders.add(order);
            }
        }
        return orders;
    }

    /**
     * Crea un nuevo pedido en la base de datos.
     *
     * @param cod objeto DTO que contiene la información del pedido a crear.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public void create(CreateOrderDto cod) throws SQLException {
        try (Connection con = hcp.getConnection()) {
            con.setAutoCommit(false);
            try {
                long generatedOrderId;
                try (PreparedStatement ps = con.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setDate(1, Date.valueOf(LocalDate.now()));
                    ps.setDate(2, Date.valueOf(cod.getRequiredDate()));
                    ps.setString(3, cod.getComments());
                    ps.setInt(4, cod.getCustomerNumber());
                    ps.executeUpdate();
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            generatedOrderId = rs.getLong(1);
                        } else {
                            throw new SQLException("No se pudo obtener la clave autogenerada para la orden.");
                        }
                    }
                    System.out.println("Insertado en orders:");
                    System.out.println("Order Number (auto-generado): " + generatedOrderId);
                    System.out.println("Order Date: " + LocalDate.now());
                    System.out.println("Required Date: " + cod.getRequiredDate());
                    System.out.println("Comments: " + cod.getComments());
                    System.out.println("Customer Number: " + cod.getCustomerNumber());
                }

                // Insertar valores en la tabla orderdetails utilizando la clave generada
                try (PreparedStatement ps = con.prepareStatement(INSERT_ORDER_DETAILS)) {
                    List<OrderDetailDto> lista = cod.getOrderDetails();

                    for (OrderDetailDto dto : lista) {
                        Optional<Product> producto = productDataAccess.findById(dto.getProductCode());
                        if (producto.isPresent()) {
                            ps.setString(1, dto.getProductCode());
                            ps.setInt(2, dto.getQuantityOrdered());
                            ps.setDouble(3, producto.get().getBuyPrice());
                            ps.executeUpdate();

                            // Mostrar los detalles insertados en orderdetails
                            System.out.println("Insertado en orderdetails:");
                            System.out.println("Product Code: " + dto.getProductCode());
                            System.out.println("Quantity Ordered: " + dto.getQuantityOrdered());
                            System.out.println("Buy Price: " + producto.get().getBuyPrice());
                        }
                    }
                }

                con.commit();
                System.out.println("Orden Creada");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
                con.rollback();
            }
        }
    }

    /**
     * Elimina un pedido por su ID.
     *
     * @param id el ID del pedido a eliminar.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public void deleteById(int id) throws SQLException {
        try (PreparedStatement preparedStatement = hcp.getConnection().prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.printf("Se ha borrado la orden %d\nFilas borradas: %d\n", id, filasAfectadas);
        }
    }
}
