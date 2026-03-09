package org.example.practicah2.services;

import org.example.practicah2.dto.AddItemDto;
import org.example.practicah2.dto.CartItemDto;
import org.example.practicah2.dto.CartItemsDto;
import org.example.practicah2.entities.CartItems;
import org.example.practicah2.entities.Customer;
import org.example.practicah2.entities.Product;
import org.example.practicah2.repositories.CartRepository;
import org.example.practicah2.repositories.CustomerRepository;
import org.example.practicah2.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImp implements CartItemService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public CartItemServiceImp(CartRepository cartRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CartItemDto> findCartByCustomerId(Integer customer_id) {
        List<CartItemDto> cartItems = cartRepository.findCartByCustomerId(customer_id);
        return cartItems;
    }

    @Override
    public CartItemsDto addToCart(Integer customerId, AddItemDto addItemDto) {
        Product product = productRepository.findById(addItemDto.getProductId()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        CartItems existingCartItem = cartRepository.findByCustomerAndProduct(customer, product);
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + addItemDto.getQuantity());
        } else {
            existingCartItem = new CartItems();
            existingCartItem.setCustomer(customer);
            existingCartItem.setProduct(product);
            existingCartItem.setQuantity(addItemDto.getQuantity());
        }
        cartRepository.save(existingCartItem);
        List<CartItemDto> cartItems = cartRepository.findCartByCustomer(customer);
        Double total = 0.0;
        for (CartItemDto cartItem : cartItems) {
            total += cartItem.getProductPrice();
        }
        return new CartItemsDto(cartItems, total);
    }

    @Override
    public CartItemsDto findCartByCustomerIdWithTotal(Integer customerId) {
        List<CartItemDto> cartItems = cartRepository.findCartByCustomerId(customerId);
        Double total = 0.0;
        for (CartItemDto cartItem : cartItems) {
            total += cartItem.getProductPrice() * cartItem.getQuantity();
        }
        return new CartItemsDto(cartItems, total);
    }


    @Override
    public void deleteProductFromCart(Integer productId, Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        if (customer == null || product == null) {
            throw new RuntimeException("Cliente o producto no encontrado");
        }
        CartItems cartItem = cartRepository.findByCustomerAndProduct(customer, product);
        if (cartItem != null) {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartRepository.save(cartItem);
            } else {
                cartRepository.delete(cartItem);
            }
        }
    }
    @Override
    public CartItemsDto emptyCart(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Cliente no encontrado");
        }
        List<CartItems> cartItems = cartRepository.findByCustomer(customer);
        if (!cartItems.isEmpty()) {
            cartRepository.deleteAll(cartItems);
        }
        return new CartItemsDto(List.of(), 0.0);
    }

}

