package dataaccess;

import dto.CreateOrderDto;
import entities.Employee;
import entities.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OrderDataAccess {
    long count() throws SQLException;

    boolean existsById(int id) throws SQLException;

    Optional<Order> findById(int id) throws SQLException;

    List<Order> findAll() throws SQLException;

    void create (CreateOrderDto cod) throws SQLException;

    void deleteById(int id) throws SQLException;
}
