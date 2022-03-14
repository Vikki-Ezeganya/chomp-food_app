package com.vikki.chompfooddelivery.dto.request;


import lombok.Data;
import java.util.List;

@Data
public class CartRequest {
    private  String userId;
    private List<CartItemRequest> cartItems;
    private Integer cartQuantity;
    private Integer subtotal;
}
