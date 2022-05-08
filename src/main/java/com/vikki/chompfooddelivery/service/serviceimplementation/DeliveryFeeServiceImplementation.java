package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.model.DeliveryFee;
import com.vikki.chompfooddelivery.repository.DeliveryFeeRepository;
import com.vikki.chompfooddelivery.service.DeliveryFeeService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryFeeServiceImplementation implements DeliveryFeeService {
    DeliveryFeeRepository deliveryFeeRepository;

    @Override
    public void createDeliveryFee(Integer amount) {
        DeliveryFee deliveryFee = new DeliveryFee();
        deliveryFee.setAmount(amount);
        deliveryFeeRepository.save(deliveryFee);

    }
}
