package com.vikki.chompfooddelivery.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {

    private String userId;
    private String cartId;
    private List<CartItemDto> cartItems;
    private Integer cartQuantity;
    private Integer subTotal;
}