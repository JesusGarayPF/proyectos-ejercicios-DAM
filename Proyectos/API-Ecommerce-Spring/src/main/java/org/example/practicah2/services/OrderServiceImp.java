package org.example.practicah2.services;

import org.example.practicah2.entities.*;
import org.example.practicah2.repositories.*;
import org.example.practicah2.dto.ShipmentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
@Service
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ShipmentRepository shipmentRepository;

    public OrderServiceImp(OrderRepository orderRepository, CartRepository cartRepository,
                           CustomerRepository customerRepository, ProductRepository productRepository, ShipmentRepository shipmentRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    @Transactional
    public Order completeOrder(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        List<CartItems> cartItems = cartRepository.findByCustomer(customer);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("El carrito de compras está vacío.");
        }

        double total = 0.0;
        for (CartItems item : cartItems) {
            if (item.getProduct().getStock() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + item.getProduct().getName());
            }
            total += item.getProduct().getPrice() * item.getQuantity();
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrder_total(total);
        order.setOrder_date(Timestamp.from(Instant.now()));

        order = orderRepository.save(order);

        for (CartItems item : cartItems) {
            item.getProduct().setStock(item.getProduct().getStock() - item.getQuantity());
            productRepository.save(item.getProduct());
        }

        cartRepository.deleteAll(cartItems);

        return order;
    }

    @Override
    @Transactional
    public Shipment sendOrder(Integer orderId, ShipmentDto shipmentDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        Shipment shipment = new Shipment();
        shipment.setOrder(order);
        shipment.setShipment_date(Timestamp.from(Instant.now()));
        shipment.setZip_code(shipmentDto.getZip_code());
        shipment.setCity(shipmentDto.getCity());
        shipment.setCountry(shipmentDto.getCountry());
        shipment.setState(shipmentDto.getState());
        shipment.setAddress(shipmentDto.getAddress());

        return shipmentRepository.save(shipment);
    }
}

