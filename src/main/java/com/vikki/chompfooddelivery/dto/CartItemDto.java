package com.vikki.chompfooddelivery.dto;

import lombok.Data;

@Data
public class CartItemDto {
//    private Long cartItemId;
    private Long userId;
    private Long menuId;
    private Integer quantity;
}
