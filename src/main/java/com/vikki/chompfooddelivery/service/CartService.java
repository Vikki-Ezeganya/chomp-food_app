package com.vikki.chompfooddelivery.service;


import com.vikki.chompfooddelivery.dto.CartItemDto;

public interface CartService {
    Integer addMenuItemsToCart(CartItemDto cartItemDto);

}
