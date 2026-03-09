package org.example.practicah2.controllers;

import org.example.practicah2.dto.AddItemDto;
import org.example.practicah2.dto.CartItemDto;
import org.example.practicah2.dto.CartItemsDto;
import org.example.practicah2.entities.Customer;
import org.example.practicah2.repositories.CustomerRepository;
import org.example.practicah2.services.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cart")
public class CartController {
    private final CartItemService cartItemService;
    private final CustomerRepository customerRepository;

    public CartController(CartItemService cartItemService, CustomerRepository customerRepository) {
        this.cartItemService = cartItemService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CartItemsDto> getCart(@PathVariable Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CartItemsDto(List.of(), 0.0));
        }

        CartItemsDto cart = cartItemService.findCartByCustomerIdWithTotal(customerId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<CartItemsDto> addToCart(
            @PathVariable Integer customerId,
            @RequestBody AddItemDto addItemDto) {
        CartItemsDto cartItemsDto = cartItemService.addToCart(customerId, addItemDto);
        return ResponseEntity.ok(cartItemsDto);
    }

    @DeleteMapping("/{customerId}/{productId}")
    public ResponseEntity<CartItemsDto> removeProductFromCart(
            @PathVariable Integer customerId,
            @PathVariable Integer productId) {
        cartItemService.deleteProductFromCart(productId, customerId);
        CartItemsDto updatedCart = cartItemService.findCartByCustomerIdWithTotal(customerId);
        return ResponseEntity.ok(updatedCart);
    }
    @PostMapping("/empty/{customerId}")
    public ResponseEntity<CartItemsDto> emptyCart(@PathVariable Integer customerId) {
        CartItemsDto updatedCart = cartItemService.emptyCart(customerId);
        return ResponseEntity.ok(updatedCart);
    }
}
