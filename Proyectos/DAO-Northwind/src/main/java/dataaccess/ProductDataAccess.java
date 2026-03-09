package dataaccess;

import entities.Employee;
import entities.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductDataAccess {
    long count() throws SQLException;

    boolean existsById(String id) throws SQLException;

    Optional<Product> findById(String id) throws SQLException;

    List<Product> findAll() throws SQLException;

    Product save(Product p) throws SQLException;

    void deleteById(String id) throws SQLException;
}
