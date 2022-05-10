package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.model.DeliveryFee;
import com.vikki.chompfooddelivery.repository.DeliveryFeeRepository;
import com.vikki.chompfooddelivery.service.DeliveryFeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeliveryFeeServiceImpl implements DeliveryFeeService {
    DeliveryFeeRepository deliveryFeeRepository;

    @Override
    public void createDeliveryFee(Integer amount) {
        DeliveryFee deliveryFee = new DeliveryFee();
        deliveryFee.setAmount(amount);

        if (deliveryFeeRepository != null) {
            deliveryFeeRepository.save(deliveryFee);
        }

    }

    @Override
    public Integer getDeliveryFee(Integer id) {
        var optionalDeliveryFee = deliveryFeeRepository.findById(id);
        Integer deliveryFee = null;

        if(optionalDeliveryFee.isPresent()) {
            deliveryFee = optionalDeliveryFee.get().getAmount();
        }
        return deliveryFee;
    }
}
