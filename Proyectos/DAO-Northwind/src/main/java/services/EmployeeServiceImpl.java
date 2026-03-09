package services;

import dataaccess.EmployeeDataAccess;
import entities.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de empleados.
 * Esta clase proporciona métodos para interactuar con los datos
 * de los empleados a través de la interfaz {@link EmployeeDataAccess}.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDataAccess empData;

    /**
     * Constructor de la clase EmployeeServiceImpl.
     *
     * @param empData instancia de {@link EmployeeDataAccess} para acceder a los datos de empleados.
     */
    public EmployeeServiceImpl(EmployeeDataAccess empData) {
        this.empData = empData;
    }

    /**
     * Cuenta el número total de empleados.
     *
     * @return el número total de empleados.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public long count() throws SQLException {
        return empData.count();
    }

    /**
     * Verifica si un empleado existe dado su ID.
     *
     * @param id el ID del empleado.
     * @return true si el empleado existe, false en caso contrario.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public boolean existsById(int id) throws SQLException {
        return empData.existsById(id);
    }

    /**
     * Busca un empleado por su ID.
     *
     * @param id el ID del empleado.
     * @return un Optional que contiene el empleado si existe, vacío en caso contrario.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public Optional<Employee> findById(int id) throws SQLException {
        return empData.findById(id);
    }

    /**
     * Recupera todos los empleados.
     *
     * @return una lista de todos los empleados.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public List<Employee> findAll() throws SQLException {
        return empData.findAll();
    }

    /**
     * Guarda o actualiza un empleado en la base de datos.
     *
     * @param e el empleado a guardar o actualizar.
     * @return el empleado guardado o actualizado.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public Employee save(Employee e) throws SQLException {
        return empData.save(e);
    }

    /**
     * Elimina un empleado por su ID.
     *
     * @param id el ID del empleado a eliminar.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public void deleteById(int id) throws SQLException {
        empData.deleteById(id);
    }
}
