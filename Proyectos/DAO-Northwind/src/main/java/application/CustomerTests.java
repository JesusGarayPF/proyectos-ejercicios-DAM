package application;

import entities.Customer;
import services.CustomerService;
import services.ProductService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Clase para realizar pruebas de operaciones con clientes usando CustomerService.
 */
public class CustomerTests {
    private final CustomerService customerService;

    /**
     * Constructor que inicializa la clase con el servicio de clientes.
     *
     * @param customerService instancia del servicio de clientes a utilizar.
     */
    public CustomerTests(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Cuenta el número total de clientes registrados en el sistema.
     *
     * @return el número total de clientes.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public long count() throws SQLException {
        return customerService.count();
    }

    /**
     * Verifica si existe un cliente con un ID específico.
     *
     * @param id el ID del cliente que se quiere verificar.
     * @return true si el cliente existe, false en caso contrario.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public boolean existById(int id) throws SQLException {
        return customerService.existsById(id);
    }

    /**
     * Busca un cliente por su ID.
     *
     * @param id el ID del cliente a buscar.
     * @return un Optional que contiene al cliente si es encontrado, o un Optional vacío en caso contrario.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public Optional<Customer> findById(int id) throws SQLException {
        return customerService.findById(id);
    }

    /**
     * Obtiene una lista de todos los clientes registrados.
     *
     * @return una lista con todos los clientes.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public List<Customer> findAll() throws SQLException {
        return customerService.findAll();
    }

    /**
     * Guarda un nuevo cliente en el sistema.
     *
     * @param customer el cliente que se desea guardar.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public void testSaveCustomer(Customer customer) throws SQLException {
        System.out.println(customerService.save(customer));
    }

    /**
     * Elimina un cliente del sistema usando su ID.
     *
     * @param id el ID del cliente que se desea eliminar.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public void deleteById(int id) throws SQLException {
        customerService.deleteById(id);
    }
}
