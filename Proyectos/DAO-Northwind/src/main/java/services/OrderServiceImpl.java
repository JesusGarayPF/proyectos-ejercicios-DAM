package services;

import dataaccess.OrderDataAccess;
import dto.CreateOrderDto;
import entities.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de órdenes.
 * Esta clase proporciona métodos para interactuar con los datos
 * de las órdenes a través de la interfaz {@link OrderDataAccess}.
 */
public class OrderServiceImpl implements OrderService {
    private final OrderDataAccess orderDataAccess;

    /**
     * Constructor de la clase OrderServiceImpl.
     *
     * @param orderDataAccess instancia de {@link OrderDataAccess} para acceder a los datos de órdenes.
     */
    public OrderServiceImpl(OrderDataAccess orderDataAccess) {
        this.orderDataAccess = orderDataAccess;
    }

    /**
     * Cuenta el número total de órdenes.
     *
     * @return el número total de órdenes.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public long count() throws SQLException {
        return orderDataAccess.count();
    }

    /**
     * Verifica si una orden existe dado su ID.
     *
     * @param id el ID de la orden.
     * @return true si la orden existe, false en caso contrario.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public boolean existsById(int id) throws SQLException {
        return orderDataAccess.existsById(id);
    }

    /**
     * Busca una orden por su ID.
     *
     * @param id el ID de la orden.
     * @return un Optional que contiene la orden si existe, vacío en caso contrario.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public Optional<Order> findById(int id) throws SQLException {
        return orderDataAccess.findById(id);
    }

    /**
     * Recupera todas las órdenes.
     *
     * @return una lista de todas las órdenes.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public List<Order> findAll() throws SQLException {
        return orderDataAccess.findAll();
    }

    /**
     * Crea una nueva orden utilizando el DTO proporcionado.
     *
     * @param cod el DTO que contiene los datos necesarios para crear la orden.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public void create(CreateOrderDto cod) throws SQLException {
        orderDataAccess.create(cod);
    }

    /**
     * Elimina una orden por su ID.
     *
     * @param id el ID de la orden a eliminar.
     * @throws SQLException si ocurre un error durante la operación SQL.
     */
    @Override
    public void deleteById(int id) throws SQLException {
        orderDataAccess.deleteById(id);
    }
}
