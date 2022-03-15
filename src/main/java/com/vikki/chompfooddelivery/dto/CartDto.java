package com.vikki.chompfooddelivery.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartDto {

    private String userId;
    private String cartId;
    private List<CartItemDto> cartItems;
    private Integer cartQuantity;
    private Integer subTotal;
}