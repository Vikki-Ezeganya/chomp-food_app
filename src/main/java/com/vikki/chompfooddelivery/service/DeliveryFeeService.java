package com.vikki.chompfooddelivery.service;


import com.vikki.chompfooddelivery.model.DeliveryFee;

import java.util.List;

public interface DeliveryFeeService {

    List<DeliveryFee> getAllDeliveryAmounts();
    DeliveryFee createDeliveryFee(Integer amount);
    DeliveryFee getDeliveryFee(Integer amount);
    void removeDeliveryFee(Integer amount);

}
