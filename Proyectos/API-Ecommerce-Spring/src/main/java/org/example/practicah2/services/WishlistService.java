package org.example.practicah2.services;

import org.example.practicah2.dto.WishlistDto;
import org.example.practicah2.entities.Wishlist;

import java.util.List;
import java.util.Optional;

public interface WishlistService {
    List<Wishlist> findWishlistsByCustomer(Integer customerId);
    Wishlist createWishlist(Integer customerId, WishlistDto wishlistDto);
    void deleteWishlist(Integer wishlistId);
    Optional<Wishlist> getWishlistById(Integer wishlistId);
}
