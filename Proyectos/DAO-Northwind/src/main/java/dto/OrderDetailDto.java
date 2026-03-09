package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * DTO (Data Transfer Object) que representa los detalles de un producto
 * en un pedido, incluyendo el código del producto y la cantidad
 * ordenada de ese producto.
 */
@AllArgsConstructor
@Getter
@ToString
public class OrderDetailDto {

    /** Código del producto asociado a este detalle del pedido. */
    private String productCode;

    /** Cantidad del producto que se ha ordenado. */
    private int quantityOrdered;

}

