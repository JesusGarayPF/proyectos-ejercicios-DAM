package org.example.practicah2.services;

import org.example.practicah2.dto.AddItemDto;
import org.example.practicah2.dto.CartItemDto;
import org.example.practicah2.dto.CartItemsDto;
import org.example.practicah2.entities.Customer;

import java.util.List;

public interface CartItemService {
    List<CartItemDto> findCartByCustomerId(Integer customer_id);
    CartItemsDto addToCart(Integer customerId, AddItemDto addItemDto);

    CartItemsDto findCartByCustomerIdWithTotal(Integer customerId);

    void deleteProductFromCart(Integer productId, Integer customerId);

    CartItemsDto emptyCart(Integer customerId);
}
