package com.vikki.chompfooddelivery.dto;

import com.vikki.chompfooddelivery.model.MenuItem;
import com.vikki.chompfooddelivery.model.User;
import lombok.Data;

@Data
public class CartItemDto {
//    private Long cartItemId;
    private Long userId;
    private User user;
    private Long MenuItemId;
    private MenuItem menuItem;
    private Integer quantity;
}
