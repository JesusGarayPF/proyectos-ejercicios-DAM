package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO (Data Transfer Object) para la creación de un nuevo pedido.
 * Este objeto encapsula los datos necesarios para crear una orden,
 * incluyendo la fecha requerida, comentarios, el número del cliente
 * y los detalles de los productos en la orden.
 */
@AllArgsConstructor
@Getter
@ToString
public class CreateOrderDto {

    /** Fecha y hora en que se requiere el pedido. */
    private LocalDate requiredDate;

    /** Comentarios adicionales sobre el pedido. */
    private String comments;

    /** Número del cliente que realiza el pedido. */
    private int customerNumber;

    /** Lista de detalles de los productos en el pedido. */
    private List<OrderDetailDto> orderDetails;

}



