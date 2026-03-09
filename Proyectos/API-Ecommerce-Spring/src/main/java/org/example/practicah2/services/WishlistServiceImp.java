package org.example.practicah2.services;

import org.example.practicah2.dto.WishlistDto;
import org.example.practicah2.entities.Customer;
import org.example.practicah2.entities.Wishlist;
import org.example.practicah2.repositories.CustomerRepository;
import org.example.practicah2.repositories.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImp implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final CustomerRepository customerRepository;

    public WishlistServiceImp(WishlistRepository wishlistRepository, CustomerRepository customerRepository) {
        this.wishlistRepository = wishlistRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Wishlist> findWishlistsByCustomer(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Cliente no encontrado");
        }
        return wishlistRepository.findByCustomer(customer);
    }

    @Override
    public Wishlist createWishlist(Integer customerId, WishlistDto wishlistDto) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Cliente no encontrado");
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setCustomer(customer);
        wishlist.setName(wishlistDto.getName());
        wishlist.setShared(wishlistDto.getShared());
        return wishlistRepository.save(wishlist);
    }

    @Override
    public void deleteWishlist(Integer wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElse(null);
        if (wishlist == null) {
            throw new RuntimeException("Lista de deseos no encontrada");
        }
        if (!wishlist.getListaProductos().isEmpty()) {
            throw new RuntimeException("No se puede eliminar una lista de deseos con productos.");
        }
        wishlistRepository.delete(wishlist);
    }

    @Override
    public Optional<Wishlist> getWishlistById(Integer wishlistId) {
        return wishlistRepository.findById(wishlistId);
    }
}
