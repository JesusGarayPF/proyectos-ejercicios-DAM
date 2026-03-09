package org.example.practicah2.repositories;

import org.example.practicah2.entities.Order;
import org.example.practicah2.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(Customer customer);
}

