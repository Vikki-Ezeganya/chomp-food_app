package com.vikki.chompfooddelivery.dto.request;


import lombok.Data;

@Data
public class CartItemRequest {

    private Long userId;
    private Long menuId;
    private Integer quantity;

}
