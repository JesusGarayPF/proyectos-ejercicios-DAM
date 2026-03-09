package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Representa una entidad Customer de la base de datos.
 * La clase corresponde a la tabla "customers" de la base de datos.
 * Proporciona un constructor, getters y un método `toString' para acceder a los atributos.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Customer {
    /**
     * `customerNumber`: Un identificador único para el cliente.
     */
    private int customerNumber;
    /**
     * El nombre del cliente.
     */
    private String customerName;
    /**
     * `contactLastName`: El apellido del contacto.
     */
    private String contactLastName;
    /**
     * `contactFirstName`: El nombre del contacto.
     */
    private String contactFirstName;
    /**
     * `phone`: El teléfono del contacto.
     */
    private String phone;
    /**
     * `addressLine1`: La primera línea de la dirección del cliente.
     */
    private String addressLine1;
    /**
     * `addressLine2`: La segunda línea de la dirección del cliente.
     */
    private String addressLine2;
    /**
     * `city`: La ciudad donde vive el cliente.
     */
    private String city;
    /**
     * `state`: El estado o provincia donde vive el cliente.
     */
    private String state;
    /**
     * `postalCode`: El código postal del cliente.
     */
    private String postalCode;
    /**
     * `country`: El país del cliente.
     */
    private String country;
    /**
     * `salesRepEmployeeNumber`: Identificador del representante de ventas.
     */
    private int salesRepEmployeeNumber;
    /**
     * `creditLimit`: Límite de crédito asignado.
     */
    private float creditLimit;

}