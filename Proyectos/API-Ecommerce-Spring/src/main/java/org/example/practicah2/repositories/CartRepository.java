package org.example.practicah2.repositories;

import org.example.practicah2.dto.CartItemDto;
import org.example.practicah2.entities.CartItems;
import org.example.practicah2.entities.Customer;
import org.example.practicah2.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItems, Integer> {
    @Query("SELECT new org.example.practicah2.dto.CartItemDto(ci.product.name, ci.product.price, ci.quantity) " +
            "FROM CartItems ci " +
            "WHERE ci.customer.customer_id = :customerId " +
            "ORDER BY ci.product.name ASC")
    List<CartItemDto> findCartByCustomerId(@Param("customerId") Integer customerId);
    CartItems findByCustomerAndProduct(Customer customer, Product product);

    @Query("SELECT new org.example.practicah2.dto.CartItemDto(ci.product.name, ci.product.price, ci.quantity) " +
            "FROM CartItems ci " +
            "WHERE ci.customer = :customer " +
            "ORDER BY ci.product.name ASC")
    List<CartItemDto> findCartByCustomer(@Param("customer") Customer customer);
    List<CartItems> findByCustomer(Customer customer);
}
