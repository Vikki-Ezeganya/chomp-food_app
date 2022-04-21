package com.vikki.chompfooddelivery.service;


import com.vikki.chompfooddelivery.dto.CartItemDto;
import com.vikki.chompfooddelivery.model.CartItem;

import java.util.List;

public interface CartService {
    Integer addMenuItemsToCart(CartItemDto cartItemDto);
    CartItemDto editCartItem(Long cartItemId, Integer quantity);
    void deleteCartItem(Long cartItemId);
    List<CartItemDto> getAllCartItems();
    CartItem getCartItemById(Long cartItemId);
    String deleteCartItems();
}
