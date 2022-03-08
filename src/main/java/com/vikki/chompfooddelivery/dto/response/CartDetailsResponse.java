package com.vikki.chompfooddelivery.dto.response;

import lombok.Data;

@Data
public class CartDetailsResponse {
    private String userId;
    private String productId;
    private Integer quantity;
}
