package application;

import entities.Customer;
import entities.Employee;
import services.EmployeeService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Clase para realizar pruebas de operaciones con empleados usando EmployeeService.
 */
public class EmployeeTests {
    private final EmployeeService empService;

    /**
     * Constructor que inicializa la clase con el servicio de empleados.
     *
     * @param empService instancia del servicio de empleados a utilizar.
     */
    public EmployeeTests(EmployeeService empService) {
        this.empService = empService;
    }

    /**
     * Cuenta el número total de empleados registrados en el sistema.
     *
     * @return el número total de empleados.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public long count() throws SQLException {
        return empService.count();
    }

    /**
     * Verifica si existe un empleado con un ID específico.
     *
     * @param id el ID del empleado que se quiere verificar.
     * @return true si el empleado existe, false en caso contrario.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public boolean existById(int id) throws SQLException {
        return empService.existsById(id);
    }

    /**
     * Busca un empleado por su ID.
     *
     * @param id el ID del empleado a buscar.
     * @return un Optional que contiene al empleado si es encontrado, o un Optional vacío en caso contrario.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public Optional<Employee> findById(int id) throws SQLException {
        return empService.findById(id);
    }

    /**
     * Obtiene una lista de todos los empleados registrados.
     *
     * @return una lista con todos los empleados.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public List<Employee> findAll() throws SQLException {
        return empService.findAll();
    }

    /**
     * Guarda un nuevo empleado en el sistema.
     *
     * @param e el empleado que se desea guardar.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public void testSaveEmployee(Employee e) throws SQLException {
        System.out.println(empService.save(e));
    }

    /**
     * Elimina un empleado del sistema usando su ID.
     *
     * @param id el ID del empleado que se desea eliminar.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public void deleteById(int id) throws SQLException {
        empService.deleteById(id);
    }
}
