package com.vikki.chompfooddelivery.service;

import com.vikki.chompfooddelivery.dto.CartDto;

public interface CartService {
    CartDto createCartItem(CartDto cartDto);
}
