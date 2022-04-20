package com.vikki.chompfooddelivery.dto;

import com.vikki.chompfooddelivery.model.MenuItem;
import lombok.Data;

@Data
public class CartItemDto {
//    private Long cartItemId;
    private Long userId;
    private MenuItem menuItem;
    private Long MenuItemId;
    private Integer quantity;
}
