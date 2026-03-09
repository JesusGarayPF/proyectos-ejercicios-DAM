package application;

import entities.Product;
import services.ProductService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Clase para realizar pruebas de operaciones con productos usando ProductService.
 */
public class ProductTests {

    private final ProductService productService;

    /**
     * Constructor que inicializa la clase con el servicio de productos.
     *
     * @param productDataAccess instancia del servicio de productos a utilizar.
     */
    public ProductTests(ProductService productDataAccess) {
        this.productService = productDataAccess;
    }

    /**
     * Cuenta el número total de productos registrados en el sistema.
     *
     * @return el número total de productos.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public long count() throws SQLException {
        return productService.count();
    }

    /**
     * Verifica si existe un producto con un ID específico.
     *
     * @param id el ID del producto que se quiere verificar.
     * @return true si el producto existe, false en caso contrario.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public boolean existsById(String id) throws SQLException {
        return productService.existsById(id);
    }

    /**
     * Busca un producto por su ID.
     *
     * @param id el ID del producto a buscar.
     * @return un Optional que contiene el producto si es encontrado, o un Optional vacío en caso contrario.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public Optional<Product> findById(String id) throws SQLException {
        return productService.findById(id);
    }

    /**
     * Obtiene una lista de todos los productos registrados.
     *
     * @return una lista con todos los productos.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public List<Product> findAll() throws SQLException {
        return productService.findAll();
    }

    /**
     * Guarda un nuevo producto en el sistema.
     *
     * @param product el producto que se desea guardar.
     * @return el producto guardado.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public Product save(Product product) throws SQLException {
        return productService.save(product);
    }

    /**
     * Elimina un producto del sistema usando su ID.
     *
     * @param id el ID del producto que se desea eliminar.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public void deleteById(String id) throws SQLException {
        productService.deleteById(id);
    }
}
