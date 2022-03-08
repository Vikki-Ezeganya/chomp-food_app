package com.vikki.chompfooddelivery.dto.request;

import lombok.Data;

@Data
public class CartDetailsRequest {

    private String userId;
    private String productId;
    private Integer quantity;

}
