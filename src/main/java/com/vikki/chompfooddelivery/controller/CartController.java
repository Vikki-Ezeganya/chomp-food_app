package com.vikki.chompfooddelivery.controller;

import com.vikki.chompfooddelivery.dto.CartDto;
import com.vikki.chompfooddelivery.dto.request.CartDetailsRequest;
import com.vikki.chompfooddelivery.dto.response.CartDetailsResponse;
import com.vikki.chompfooddelivery.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;



    @PostMapping
    public CartDetailsResponse createCart(@RequestBody CartDetailsRequest request) {
        CartDetailsResponse response = new CartDetailsResponse();

        CartDto cartDto = new CartDto();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(request, CartDto.class);

        CartDto createdItem = cartService.createCartItem(cartDto);

        return response;
    }

}
