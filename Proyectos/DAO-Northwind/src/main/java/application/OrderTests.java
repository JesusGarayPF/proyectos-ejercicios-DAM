package application;

import dto.CreateOrderDto;
import services.OrderService;
import entities.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Clase para realizar pruebas de operaciones con órdenes usando OrderService.
 */
public class OrderTests {
    private final OrderService orderService;

    /**
     * Constructor que inicializa la clase con el servicio de órdenes.
     *
     * @param orderService instancia del servicio de órdenes a utilizar.
     */
    public OrderTests(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Cuenta el número total de órdenes registradas en el sistema.
     *
     * @return el número total de órdenes.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public long count() throws SQLException {
        return orderService.count();
    }

    /**
     * Verifica si existe una orden con un ID específico.
     *
     * @param id el ID de la orden que se quiere verificar.
     * @return true si la orden existe, false en caso contrario.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public boolean existById(int id) throws SQLException {
        return orderService.existsById(id);
    }

    /**
     * Busca una orden por su ID.
     *
     * @param id el ID de la orden a buscar.
     * @return un Optional que contiene la orden si es encontrada, o un Optional vacío en caso contrario.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public Optional<Order> findById(int id) throws SQLException {
        return orderService.findById(id);
    }

    /**
     * Obtiene una lista de todas las órdenes registradas.
     *
     * @return una lista con todas las órdenes.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public List<Order> findAll() throws SQLException {
        return orderService.findAll();
    }

    /**
     * Crea una nueva orden en el sistema utilizando un DTO (Data Transfer Object).
     *
     * @param cod el DTO que contiene la información para crear la orden.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public void testCreateOrder(CreateOrderDto cod) throws SQLException {
        orderService.create(cod);
    }

    /**
     * Elimina una orden del sistema usando su ID.
     *
     * @param id el ID de la orden que se desea eliminar.
     * @throws SQLException si ocurre algún error de acceso a la base de datos.
     */
    public void deleteById(int id) throws SQLException {
        orderService.deleteById(id);
    }
}
