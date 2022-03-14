package com.vikki.chompfooddelivery.controller;

import com.vikki.chompfooddelivery.dto.CartDto;
import com.vikki.chompfooddelivery.dto.request.CartRequest;
import com.vikki.chompfooddelivery.dto.response.CartResponse;
import com.vikki.chompfooddelivery.service.CartService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CartController {

    private CartService cartService;



    @PostMapping
    public CartResponse createCart(@RequestBody CartRequest request) {
        CartResponse response = new CartResponse();

        ModelMapper modelMapper = new ModelMapper();
        CartDto cartDto = modelMapper.map(request, CartDto.class);

        CartDto createdCart =  cartService.createCart(cartDto);

        modelMapper.map(createdCart, CartResponse.class);

        return response;
    }

}
