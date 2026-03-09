package services;

import dataaccess.ProductDataAccess;
import entities.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de productos.
 * Esta clase proporciona métodos para interactuar con los datos
 * de los productos a través de la interfaz{@link ProductDataAccess } .
 */
public class ProductServiceImpl implements ProductService {

    private final ProductDataAccess productDataAccess;

    /**
     * Constructor de la clase ProductServiceImpl.
     *
     * @param productDataAccess instancia de{@link ProductDataAccess}  para acceder a los datos de productos.
     */
    public ProductServiceImpl(ProductDataAccess productDataAccess) {
        this.productDataAccess = productDataAccess;
    }

    /**
     * Cuenta el número total de productos.
     *
     * @return el número total de productos.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public long count() throws SQLException {
        return productDataAccess.count();
    }

    /**
     * Verifica si un producto existe dado su código.
     *
     * @param id el código del producto.
     * @return true si el producto existe, false en caso contrario.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public boolean existsById(String id) throws SQLException {
        return productDataAccess.existsById(id);
    }

    /**
     * Busca un producto por su código.
     *
     * @param id el código del producto.
     * @return un Optional que contiene el producto si existe, vacío en caso contrario.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public Optional<Product> findById(String id) throws SQLException {
        return productDataAccess.findById(id);
    }

    /**
     * Recupera todos los productos.
     *
     * @return una lista de todos los productos.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public List<Product> findAll() throws SQLException {
        return productDataAccess.findAll();
    }

    /**
     * Guarda o actualiza un producto en la base de datos.
     *
     * @param product el producto a guardar o actualizar.
     * @return el producto guardado.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public Product save(Product product) throws SQLException {
        return productDataAccess.save(product);
    }

    /**
     * Elimina un producto por su código.
     *
     * @param id el código del producto a eliminar.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public void deleteById(String id) throws SQLException {
        productDataAccess.deleteById(id);
    }
}

