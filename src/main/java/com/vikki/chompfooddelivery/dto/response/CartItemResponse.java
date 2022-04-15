package com.vikki.chompfooddelivery.dto.response;

import lombok.Data;

@Data
public class CartItemResponse {
    private Long userId;
    private Long menuId;
    private Integer quantity;

}
