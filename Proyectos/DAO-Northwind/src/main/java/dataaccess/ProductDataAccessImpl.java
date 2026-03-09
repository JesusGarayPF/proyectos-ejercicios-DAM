package dataaccess;

import entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz ProductDataAccess para manejar las operaciones de base de datos relacionadas con los productos.
 * Esta clase proporciona métodos para contar, buscar, crear, actualizar y eliminar productos en la base de datos.
 */
public class ProductDataAccessImpl implements ProductDataAccess {

    /** Conexión al pool de conexiones. */
    private final Connection pool;

    /**
     * Constructor que inicializa la conexión al pool de conexiones.
     *
     * @throws SQLException si ocurre un error al obtener la conexión.
     */
    public ProductDataAccessImpl() throws SQLException {
        this.pool = HikariConnectionPool.getInstance().getConnection();
    }

    /**
     * Cuenta la cantidad total de productos en stock en la base de datos.
     *
     * @return la cantidad total de productos en stock.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public long count() throws SQLException {
        long totalQuantityInStock = 0;
        try (PreparedStatement ps = pool.prepareStatement("SELECT quantityInStock FROM products")) {
            ps.execute();
            try (ResultSet rs = ps.getResultSet()) {
                while (rs.next()) {
                    totalQuantityInStock += rs.getInt("quantityInStock");
                }
            }
        }
        return totalQuantityInStock;
    }

    /**
     * Verifica si existe un producto por su código.
     *
     * @param id el código del producto.
     * @return true si el producto existe, false en caso contrario.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public boolean existsById(String id) throws SQLException {
        try (PreparedStatement ps = pool.prepareStatement("SELECT * FROM products WHERE productCode = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    /**
     * Busca un producto por su código.
     *
     * @param id el código del producto.
     * @return un Optional que contiene el producto si existe, vacío en caso contrario.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public Optional<Product> findById(String id) throws SQLException {
        try (PreparedStatement ps = pool.prepareStatement("SELECT * FROM products WHERE productCode = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String productCode = rs.getString("productCode");
                    String productName = rs.getString("productName");
                    String productLine = rs.getString("productLine");
                    String productScale = rs.getString("productScale");
                    String productVendor = rs.getString("productVendor");
                    String productDescription = rs.getString("productDescription");
                    int quantityInStock = rs.getInt("quantityInStock");
                    double buyPrice = rs.getDouble("buyPrice");
                    double mSRP = rs.getDouble("MSRP");
                    Product product = new Product(productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, mSRP);
                    return Optional.of(product);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    /**
     * Recupera todos los productos de la base de datos.
     *
     * @return una lista de todos los productos.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement ps = pool.prepareStatement("SELECT * from products")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String productCode = rs.getString("productCode");
                    String productName = rs.getString("productName");
                    String productLine = rs.getString("productLine");
                    String productScale = rs.getString("productScale");
                    String productVendor = rs.getString("productVendor");
                    String productDescription = rs.getString("productDescription");
                    int quantityInStock = rs.getInt("quantityInStock");
                    double buyPrice = rs.getDouble("buyPrice");
                    double mSRP = rs.getDouble("MSRP");
                    products.add(new Product(productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, mSRP));
                }
            }
        }
        return products;
    }

    /**
     * Guarda o actualiza un producto en la base de datos.
     *
     * @param p el producto a guardar o actualizar.
     * @return el producto guardado o actualizado.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public Product save(Product p) throws SQLException {
        if (existsById(p.getProductCode())) {
            String sqlUpdate = "UPDATE products SET productName = ?, productLine = ?, productScale = ?, productVendor = ?, productDescription = ?, quantityInStock = ?, buyPrice = ?, MSRP = ? WHERE productCode = ?";
            try (PreparedStatement ps = pool.prepareStatement(sqlUpdate)) {
                ps.setString(1, p.getProductName());
                ps.setString(2, p.getProductLine());
                ps.setString(3, p.getProductScale());
                ps.setString(4, p.getProductVendor());
                ps.setString(5, p.getProductDescription());
                ps.setInt(6, p.getQuantityInStock());
                ps.setDouble(7, p.getBuyPrice());
                ps.setDouble(8, p.getMSRP());
                ps.setString(9, p.getProductCode());
                int filasActualizadas = ps.executeUpdate();
                System.out.println("Producto actualizado. Filas afectadas: " + filasActualizadas);
            }
        } else {
            String sqlInsert = "INSERT INTO products (productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = pool.prepareStatement(sqlInsert)) {
                ps.setString(1, p.getProductCode());
                ps.setString(2, p.getProductName());
                ps.setString(3, p.getProductLine());
                ps.setString(4, p.getProductScale());
                ps.setString(5, p.getProductVendor());
                ps.setString(6, p.getProductDescription());
                ps.setInt(7, p.getQuantityInStock());
                ps.setDouble(8, p.getBuyPrice());
                ps.setDouble(9, p.getMSRP());
                int filasActualizadas = ps.executeUpdate();
                System.out.println("Producto creado. Filas afectadas: " + filasActualizadas);
            }
        }
        return p;
    }

    /**
     * Elimina un producto por su código.
     *
     * @param id el código del producto a eliminar.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public void deleteById(String id) throws SQLException {
        try (PreparedStatement ps = pool.prepareStatement("DELETE FROM products WHERE productCode = ?")) {
            ps.setString(1, id);
            int filasAfectadas = ps.executeUpdate();
            System.out.printf("Se ha borrado el producto %d\nFilas borradas: %d\n", id, filasAfectadas);
        }
    }
}
