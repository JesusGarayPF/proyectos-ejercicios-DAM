package org.example.practicah2.controllers;

import org.example.practicah2.entities.Order;
import org.example.practicah2.entities.Shipment;
import org.example.practicah2.dto.ShipmentDto;
import org.example.practicah2.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create/{customerId}")
    public ResponseEntity<?> completeOrder(@PathVariable Integer customerId) {
        try {
            Order order = orderService.completeOrder(customerId);
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/send/{orderId}")
    public ResponseEntity<?> sendOrder(@PathVariable Integer orderId,
                                       @RequestBody ShipmentDto shipmentDto) {
        try {
            Shipment shipment = orderService.sendOrder(orderId, shipmentDto);
            return ResponseEntity.ok(shipment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

