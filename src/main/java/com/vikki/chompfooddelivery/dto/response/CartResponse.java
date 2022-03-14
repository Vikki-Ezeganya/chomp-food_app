package com.vikki.chompfooddelivery.dto.response;

import lombok.Data;

@Data
public class CartResponse {
    private String userId;
    private String cartId;

    private Integer quantity;
    private Integer total;

}
