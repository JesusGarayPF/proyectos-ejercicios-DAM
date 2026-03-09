package org.example.practicah2.repositories;

import org.example.practicah2.entities.Customer;
import org.example.practicah2.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository  extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> findByCustomer(Customer customer);
}
