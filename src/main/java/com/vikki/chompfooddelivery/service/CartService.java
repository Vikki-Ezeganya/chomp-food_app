package com.vikki.chompfooddelivery.service;


import com.vikki.chompfooddelivery.dto.CartItemDto;
import com.vikki.chompfooddelivery.model.CartItem;

public interface CartService {
    Integer addMenuItemsToCart(CartItemDto cartItemDto);
    CartItemDto editCartItem(Long cartItemId, Integer quantity);
}
