package com.vikki.chompfooddelivery.dto.request;


import lombok.Data;

@Data
public class CartItemRequest {

    private String menuId;
    private Integer quantity;

}
