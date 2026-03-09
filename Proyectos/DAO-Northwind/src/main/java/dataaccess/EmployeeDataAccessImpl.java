package dataaccess;

import entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz {@link EmployeeDataAccess} para manejar las operaciones de base de datos relacionadas con los empleados.
 * Esta clase utiliza consultas SQL para interactuar con la tabla `employees`.
 * Se apoya en un {@link HikariConnectionPool} para las conexiones a la base de datos.
 * De atr
 */
public class EmployeeDataAccessImpl implements EmployeeDataAccess {
    /**
     * Pool de conexiones utilizado para manejar las conexiones a la base de datos.
     */
    private HikariConnectionPool hcp;

    /**
     * Construye una nueva instancia de {@code EmployeeDataAccessImpl} e inicializa el pool de conexiones.
     *
     * @throws SQLException si ocurre un error al establecer la conexión con la base de datos.
     */
    public EmployeeDataAccessImpl() throws SQLException {
        this.hcp = HikariConnectionPool.getInstance();
    }
    /**
     * Consulta SQL para contar el número total de empleados.
     */
    private static final String COUNT_ALL = "Select count(*) from employees";
    /**
     * Consulta SQL para obtener todos los empleados de la base de datos.
     */
    private static final String FIND_ALL_QUERY = "Select * from employees";
    /**
     * Consulta SQL para encontrar un empleado por su número de empleado.
     */
    private static final String BY_ID_QUERY = "Select * from employees where employeeNumber = ?";
    /**
     * Consulta SQL para eliminar un empleado de la base de datos por su número de empleado.
     */
    private static final String DELETE_BY_ID = "Delete from employees where employeeNumber = ? ";
    /**
     * Consulta SQL para actualizar un empleado existente
     */
    private static final String UPDATE_EMPLOYEE = """
    UPDATE employees 
    SET lastName = ?, firstName = ?, extension = ?, email = ?, officeCode = ?, reportsTo = ?, jobTitle = ?
    WHERE employeeNumber = ?
    """;
    /**
     * Consulta SQL para actualizar un empleado existente
     */
    private static final String INSERT_EMPLOYEE = """
            Insert into employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle)
            Values (?, ?, ?, ?, ?, ?, ?, ?)
            """;

    /**
     * Cuenta el número total de empleados en la base de datos.
     *
     * @return el número total de empleados.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public long count() throws SQLException {
        long count = 0;
        try (Statement st = hcp.getConnection().createStatement();
             ResultSet rs = st.executeQuery(COUNT_ALL)) {
            if (rs.next()) {
                count = rs.getLong(1);
            }
        }
        return count;
    }

    /**
     * Verifica si existe un empleado por su ID.
     *
     * @param id el ID del empleado.
     * @return true si el empleado existe, false en caso contrario.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public boolean existsById(int id) throws SQLException {
        try (PreparedStatement ps = hcp.getConnection().prepareStatement(BY_ID_QUERY)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    /**
     * Busca un empleado por su ID.
     *
     * @param id el ID del empleado.
     * @return un Optional que contiene el empleado si existe, vacío en caso contrario.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public Optional<Employee> findById(int id) throws SQLException {
        try (PreparedStatement ps = hcp.getConnection().prepareStatement(BY_ID_QUERY)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.isBeforeFirst()) {
                    rs.next();
                    int employeeNumber = rs.getInt("employeeNumber");
                    String lastName = rs.getString("lastName");
                    String firstName = rs.getString("firstName");
                    String extension = rs.getString("extension");
                    String email = rs.getString("email");
                    String officeCode = rs.getString("officeCode");
                    int reportsTo = rs.getInt("reportsTo");
                    String jobTitle = rs.getString("jobTitle");
                    Employee emp = new Employee(employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle);
                    return Optional.of(emp);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    /**
     * Recupera todos los empleados de la base de datos.
     *
     * @return una lista de todos los empleados.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> ListaEmployee = new ArrayList<>();
        try (Statement st = hcp.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(FIND_ALL_QUERY);
            while (rs.next()) {
                int employeeNumber = rs.getInt("employeeNumber");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                String extension = rs.getString("extension");
                String email = rs.getString("email");
                String officeCode = rs.getString("officeCode");
                int reportsTo = rs.getInt("reportsTo");
                String jobTitle = rs.getString("jobTitle");
                ListaEmployee.add(new Employee(employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle));
            }
        }
        return ListaEmployee;
    }

    /**
     * Guarda o actualiza un empleado en la base de datos.
     *
     * @param e el empleado a guardar.
     * @return el empleado guardado.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public Employee save(Employee e) throws SQLException {
        boolean existe = existsById(e.getEmployeeNumber());
        if (existe) {
            try (PreparedStatement ps = hcp.getConnection().prepareStatement(UPDATE_EMPLOYEE)){
                ps.setString(1, e.getLastName());
                ps.setString(2, e.getFirstName());
                ps.setString(3, e.getExtension());
                ps.setString(4, e.getEmail());
                ps.setString(5, e.getOfficeCode());
                ps.setInt(6, e.getReportsTo());
                ps.setString(7, e.getJobTitle());
                ps.setInt(8, e.getEmployeeNumber());
                int filasActualizadas = ps.executeUpdate();
                System.out.println("Filas actualizadas: " + filasActualizadas);
            }
        } else {
            try (PreparedStatement ps = hcp.getConnection().prepareStatement(INSERT_EMPLOYEE)) {
                ps.setInt(1, e.getEmployeeNumber());
                ps.setString(2, e.getLastName());
                ps.setString(3, e.getFirstName());
                ps.setString(4, e.getExtension());
                ps.setString(5, e.getEmail());
                ps.setString(6, e.getOfficeCode());
                ps.setInt(7, e.getReportsTo());
                ps.setString(8, e.getJobTitle());
                int filasInsertadas = ps.executeUpdate();
                System.out.println("Filas insertadas: " + filasInsertadas);
            }
        }
        return e;
    }

    /**
     * Elimina un empleado por su ID.
     *
     * @param id el ID del empleado a eliminar.
     * @throws SQLException si ocurre un error en la operación SQL.
     */
    @Override
    public void deleteById(int id) throws SQLException {
        try (PreparedStatement ps = hcp.getConnection().prepareStatement(DELETE_BY_ID)){
            ps.setInt(1, id);
            int filasActualizadas = ps.executeUpdate();
            System.out.printf("Se ha borrado el empleado %d\nFilas borradas: %d\n", id, filasActualizadas);
        }
    }
}
