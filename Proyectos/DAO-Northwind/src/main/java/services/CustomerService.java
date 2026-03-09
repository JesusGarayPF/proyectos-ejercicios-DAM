package services;

import entities.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    long count() throws SQLException;

    boolean existsById(int id) throws SQLException;

    Optional<Customer> findById(int id) throws SQLException;

    List<Customer> findAll() throws SQLException;

    Customer save(Customer customer) throws SQLException;

    void deleteById(int id) throws SQLException;
}
