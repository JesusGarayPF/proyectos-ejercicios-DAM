package dataaccess;

import entities.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EmployeeDataAccess {
    long count() throws SQLException;
    boolean existsById(int id) throws SQLException;
    Optional<Employee> findById(int id) throws SQLException;
    List<Employee> findAll() throws SQLException;
    Employee save (Employee e) throws SQLException;
    void deleteById(int id) throws SQLException;
}
