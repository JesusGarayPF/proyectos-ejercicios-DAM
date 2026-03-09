package services;

import entities.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    long count() throws SQLException;

    boolean existsById(String id) throws SQLException;

    Optional<Product> findById(String id) throws SQLException;

    List<Product> findAll() throws SQLException;

    Product save(Product product) throws SQLException;

    void deleteById(String id) throws SQLException;
}
