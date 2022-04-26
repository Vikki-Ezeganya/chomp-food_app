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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CartItemController {

    private CartService cartService;

    @GetMapping
    public List<CartItemResponse> getAllItemsInCart() {
       var allCartItemsDto = cartService.getAllCartItems();
       List<CartItemResponse> listOfCartItemResponse = new ArrayList<>();

       allCartItemsDto.forEach(cartItemDto -> {
           CartItemResponse cartItemResponse = new CartItemResponse();

           cartItemResponse.setMenuItemId(cartItemDto.getMenuItem().getId());
           cartItemResponse.setMenuItem(cartItemDto.getMenuItem());
           cartItemResponse.setQuantity(cartItemDto.getQuantity());
           listOfCartItemResponse.add(cartItemResponse);
       });

       return listOfCartItemResponse;
    }

    @GetMapping("/{cartItemId}")
    public CartItemResponse getItemInCart(@PathVariable(name = "cartItemId") Long cartItemId){
        var cartItem = cartService.getCartItemById(cartItemId);
        CartItemResponse cartItemResponse = new CartItemResponse();

        cartItemResponse.setMenuItem(cartItem.getMenuItem());
        cartItemResponse.setQuantity(cartItem.getQuantity());
        return cartItemResponse;
    }

    @GetMapping("/numberOfCartItems")
    public Integer getTotalNumberOfCartItems(){
        return cartService.getNumberOfCartItems();
    }

    @GetMapping("/cartSubTotal")
    public Integer getCartSubTotal() {
        return cartService.getCartSubTotal();
    }

    @PostMapping("/add")
    public String createCart(@RequestBody CartItemRequest cartItemRequest) {

        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setUserId(cartItemRequest.getUserId());
        cartItemDto.setMenuItemId(cartItemRequest.getMenuId());
        cartItemDto.setQuantity(cartItemRequest.getQuantity());

        var noOfItems = cartService.addMenuItemsToCart(cartItemDto);

        return noOfItems + " item(s) added!";
    }

    @PutMapping(path="/edit/{cartItemId}/{quantity}")
    public CartItemResponse updateCart(@PathVariable(name = "cartItemId") Long cartItemId,
                               @PathVariable(name = "quantity") Integer quantity) {

        CartItemDto cartItem = cartService.editCartItem(cartItemId, quantity);

        CartItemResponse cartItemResponse = new CartItemResponse();

        cartItemResponse.setMenuItemId(cartItem.getMenuItemId());
        cartItemResponse.setMenuItem(cartItem.getMenuItem());
        cartItemResponse.setQuantity(cartItem.getQuantity());

        return cartItemResponse;
    }

    @DeleteMapping(path = "/remove/{cartItemId}")
    public OperationStatusModel removeCartItem(@PathVariable  Long cartItemId){

        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
        operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());

        cartService.deleteCartItem(cartItemId);
        return operationStatusModel;

    }

    @DeleteMapping
    public String removeAllItemsInCart(){
        return cartService.deleteCartItems();
    }

}
