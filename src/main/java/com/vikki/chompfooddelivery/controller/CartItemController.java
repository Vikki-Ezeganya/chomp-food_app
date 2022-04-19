package com.vikki.chompfooddelivery.controller;

import com.vikki.chompfooddelivery.dto.CartItemDto;
import com.vikki.chompfooddelivery.dto.request.CartItemRequest;
import com.vikki.chompfooddelivery.dto.response.CartItemResponse;
import com.vikki.chompfooddelivery.dto.response.OperationStatusModel;
import com.vikki.chompfooddelivery.dto.response.RequestOperationName;
import com.vikki.chompfooddelivery.dto.response.RequestOperationStatus;
import com.vikki.chompfooddelivery.model.CartItem;
import com.vikki.chompfooddelivery.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CartItemController {

    private CartService cartService;

    @GetMapping
    public List<CartItem> getAllItemsInCart() {
        cartService.getAllCartItems();

    }

    @PostMapping("/add")
    public String createCart(@RequestBody CartItemRequest cartItemRequest) {

        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setUserId(cartItemRequest.getUserId());
        cartItemDto.setMenuId(cartItemRequest.getMenuId());
        cartItemDto.setQuantity(cartItemRequest.getQuantity());
        var noOfItems = this.cartService.addMenuItemsToCart(cartItemDto);

        return noOfItems + " item(s) added!";
    }

    @PutMapping(path="/edit/{cartItemId}/{quantity}")
    public CartItemResponse updateCart(@PathVariable(name = "cartItemId") Long cartItemId,
                               @PathVariable(name = "quantity") Integer quantity) {

        CartItemDto cartItem = cartService.editCartItem(cartItemId, quantity);

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cartItem, CartItemResponse.class);
    }

    @DeleteMapping(path = "/remove/{cartItemId}")
    public OperationStatusModel removeCartItem(@PathVariable  Long cartItemId){

        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
        operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());

        cartService.deleteCartItem(cartItemId);
        return operationStatusModel;

    }

}
