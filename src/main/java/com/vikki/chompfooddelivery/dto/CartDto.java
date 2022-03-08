package com.vikki.chompfooddelivery.dto;

import lombok.Data;

@Data
public class CartDto {

    private String userId;
    private String cartId;
    private String productId;
    private Integer quantity;
}