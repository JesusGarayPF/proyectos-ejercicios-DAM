package org.example.practicah2.repositories;

import org.example.practicah2.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>  {
}
