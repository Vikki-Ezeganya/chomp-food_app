package com.vikki.chompfooddelivery.dto;

import lombok.Data;

@Data
public class CartItemDto {

    private String cartItemId;
    private String menuId;
    private Integer quantity;
    private CartDto cartDto;

}
