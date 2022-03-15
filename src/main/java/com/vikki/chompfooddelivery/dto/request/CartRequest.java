package com.vikki.chompfooddelivery.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartRequest {
    private  String userId;
    private List<CartItemRequest> cartItems;
    private Integer cartQuantity;
    private Integer subtotal;

    @Override
    public String toString() {
        return "CartRequest{" +
                "userId='" + userId + '\'' +
                ", cartItems=" + cartItems +
                ", cartQuantity=" + cartQuantity +
                ", subtotal=" + subtotal +
                '}';
    }
}
