package org.example.practicah2.services;

import org.example.practicah2.entities.Order;
import org.example.practicah2.entities.Shipment;
import org.example.practicah2.dto.ShipmentDto;

public interface OrderService {
    Order completeOrder(Integer customerId);
    Shipment sendOrder(Integer orderId, ShipmentDto shipmentDto);
}

