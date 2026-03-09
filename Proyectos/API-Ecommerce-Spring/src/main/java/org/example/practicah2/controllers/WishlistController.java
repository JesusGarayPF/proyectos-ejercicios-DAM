package org.example.practicah2.controllers;

import org.example.practicah2.dto.WishlistDto;
import org.example.practicah2.entities.Wishlist;
import org.example.practicah2.services.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/list/{customerId}")
    public ResponseEntity<List<Wishlist>> getWishlists(@PathVariable Integer customerId) {
        return ResponseEntity.ok(wishlistService.findWishlistsByCustomer(customerId));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Wishlist> createWishlist(@PathVariable Integer customerId,
                                                   @RequestBody WishlistDto wishlistDto) {
        Wishlist wishlist = wishlistService.createWishlist(customerId, wishlistDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(wishlist);
    }

    @DeleteMapping("/{wishlistId}")
    public ResponseEntity<?> deleteWishlist(@PathVariable Integer wishlistId) {
        try {
            wishlistService.deleteWishlist(wishlistId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{wishlistId}")
    public ResponseEntity<Optional<Wishlist>> getWishlist(@PathVariable Integer wishlistId) {
        return ResponseEntity.ok(wishlistService.getWishlistById(wishlistId));
    }
}
