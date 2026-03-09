package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Representa un empleado en la empresa.
 * Esta clase corresponde a la tabla "employees" de la base de datos.
 * Proporciona constructores, getters y un método `toString` para acceder a los atributos del empleado.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Employee {
    /**
     * `employeeNumber`: Identificador único del empleado.
     */
    private int employeeNumber;
    /**
     * `lastName`: Apellido del empleado.
     */
    private String lastName;
    /**
     * `firstName`: Nombre del empleado.
     */
    private String firstName;
    /**
     * `extension`: Extensión telefónica del empleado.
     */
    private String extension;
    /**
     * `email`: Correo electrónico del empleado.
     */
    private String email;
    /**
     * `officeCode`: Código de la oficina del empleado.
     */
    private String officeCode;
    /**
     * `reportsTo`: Identificador del empleado supervisor.
     */
    private int reportsTo;
    /**
     * `jobTitle`: Cargo del empleado.
     */
    private String jobTitle;
}