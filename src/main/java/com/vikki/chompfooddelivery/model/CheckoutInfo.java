package com.vikki.chompfooddelivery.model;

import com.vikki.chompfooddelivery.dto.AddressDto;
import com.vikki.chompfooddelivery.dto.CartItemDto;
import com.vikki.chompfooddelivery.enums.DeliveryMethod;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CheckoutInfo {
    private String nameOfUser;//
    private String phoneNumber;//
    private DeliveryMethod deliveryMethod; //should come from the client
    private AddressDto deliveryAddress;//
    private Integer subtotal;//
    private Integer deliveryFee;//
    private Integer total;//
    private LocalDate deliveryDate;//
    private List<CartItemDto> cartItems;//
    private Integer quantity;//

}
