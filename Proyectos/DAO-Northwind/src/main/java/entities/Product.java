package entities;

import lombok.*;

/**
 * Representa un producto en la empresa.
 * Esta clase corresponde a la tabla "products" de la base de datos.
 * Proporciona constructores, getters y un método `toString` para acceder a los atributos del producto.
 */

@AllArgsConstructor
@Getter
@NoArgsConstructor
@ToString
public class Product {
    /**
     * `productCode`: Identificador único del producto.
     */
    private String productCode;
    /**
     * `productName`: Nombre del producto.
     */
    private String productName;
    /**
     * `productLine`: Línea de producto a la que pertenece.
     */
    private String productLine;
    /**
     * `productScale`: Escala del producto (por ejemplo, "1:24")
     */
    private String productScale;
    /**
     * `productVendor`: Proveedor del producto.
     */
    private String productVendor;
    /**
     * `productDescription`: Descripción del producto.
     */
    private String productDescription;
    /**
     * `quantityInStock`: Cantidad de producto en stock.
     */
    private int quantityInStock;
    /**
     * `buyPrice`: Precio de compra del producto.
     */
    private double buyPrice;
    /**
     * `MSRP`: Precio de venta sugerido (MSRP).
     */
    private double mSRP;
}