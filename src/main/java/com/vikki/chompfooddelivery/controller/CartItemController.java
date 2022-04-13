package com.vikki.chompfooddelivery.controller;

import com.vikki.chompfooddelivery.dto.CartItemDto;
import com.vikki.chompfooddelivery.dto.request.CartItemRequest;
import com.vikki.chompfooddelivery.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CartItemController {

    private CartService cartService;

    @PostMapping("/add")
    public String createCart(@RequestBody CartItemRequest cartItemRequest) {

//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        CartItemDto cartItemDto = modelMapper.map(cartItemRequest, CartItemDto.class);

        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setUserId(cartItemRequest.getUserId());
        cartItemDto.setMenuId(cartItemRequest.getMenuId());
        cartItemDto.setQuantity(cartItemRequest.getQuantity());

        var noOfItems = this.cartService.addMenuItemsToCart(cartItemDto);

        return noOfItems + " item(s) added!";
    }

}
