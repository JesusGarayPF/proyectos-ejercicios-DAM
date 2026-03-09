package services;

import dataaccess.CustomerDataAccess;
import entities.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de clientes.
 * Esta clase proporciona métodos para interactuar con los datos
 * de los clientes a través de la interfaz {@link CustomerDataAccess}.
 */
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDataAccess customerDataAccess;

    /**
     * Constructor de la clase CustomerServiceImpl.
     *
     * @param customerDataAccess instancia de {@link CustomerDataAccess} para acceder a los datos de clientes.
     */
    public CustomerServiceImpl(CustomerDataAccess customerDataAccess) {
        this.customerDataAccess = customerDataAccess;
    }

    /**
     * Cuenta el número total de clientes.
     *
     * @return el número total de clientes.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public long count() throws SQLException {
        return customerDataAccess.count();
    }

    /**
     * Verifica si un cliente existe dado su ID.
     *
     * @param id el ID del cliente.
     * @return true si el cliente existe, false en caso contrario.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public boolean existsById(int id) throws SQLException {
        return customerDataAccess.existsById(id);
    }

    /**
     * Busca un cliente por su ID.
     *
     * @param id el ID del cliente.
     * @return un Optional que contiene el cliente si existe, vacío en caso contrario.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public Optional<Customer> findById(int id) throws SQLException {
        return customerDataAccess.findById(id);
    }

    /**
     * Recupera todos los clientes.
     *
     * @return una lista de todos los clientes.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public List<Customer> findAll() throws SQLException {
        return customerDataAccess.findAll();
    }

    /**
     * Guarda o actualiza un cliente en la base de datos.
     *
     * @param customer el cliente a guardar o actualizar.
     * @return el cliente guardado o actualizado.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public Customer save(Customer customer) throws SQLException {
        return customerDataAccess.save(customer);
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param id el ID del cliente a eliminar.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public void deleteById(int id) throws SQLException {
        customerDataAccess.deleteById(id);
    }
}
