package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Representa un pedido en la empresa.
 * Esta clase corresponde a la tabla "orders" de la base de datos.
 * Proporciona constructores, getters y un método `toString` para acceder a los atributos del pedido.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Order {
    /**
     * `orderNumber`: Identificador único del pedido.
     */
    private int orderNumber;
    /**
     * `orderDate`: Fecha y hora en que se realizó el pedido.
     */
    private LocalDate orderDate;
    /**
     * `requiredDate`: Fecha y hora requerida para la entrega del pedido.
     */
    private LocalDate requiredDate;
    /**
     * `shippedDate`: Fecha y hora en que se envió el pedido.
     */
    private LocalDate shippedDate;
    /**
     * `status`: Estado actual del pedido (por ejemplo: "In progress", "Shipped", "Completed").
     */
    private String status;
    /**
     * `comments`: Comentarios adicionales sobre el pedido.
     */
    private String comments;
    /**
     * `customerNumber`: Identificador del cliente asociado al pedido.
     */
    private int customerNumber;
}