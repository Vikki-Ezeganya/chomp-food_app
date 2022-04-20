package com.vikki.chompfooddelivery.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vikki.chompfooddelivery.model.MenuItem;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartItemResponse {
//    private Long userId;
    private Long menuItemId;
    private MenuItem menuItem;
    private Integer quantity;

}
