package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.dto.response.ErrorMessages;
import com.vikki.chompfooddelivery.exceptions.DeliveryFeeServiceException;
import com.vikki.chompfooddelivery.model.DeliveryFee;
import com.vikki.chompfooddelivery.repository.DeliveryFeeRepository;
import com.vikki.chompfooddelivery.service.DeliveryFeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeliveryFeeServiceImpl implements DeliveryFeeService {
    DeliveryFeeRepository deliveryFeeRepository;

    @Override
    public List<DeliveryFee> getAllDeliveryAmounts() {
        return deliveryFeeRepository.findAll();
    }

    @Override
    public DeliveryFee createDeliveryFee(Integer amount) throws DeliveryFeeServiceException {
        DeliveryFee deliveryFee = new DeliveryFee();
        deliveryFee.setAmount(amount);
        var deliveryAmt = deliveryFeeRepository.findByAmount(amount) ;

        if (deliveryAmt != null) {
            throw new DeliveryFeeServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        deliveryFeeRepository.save(deliveryFee);
        return deliveryFee;
    }

    @Override
    public DeliveryFee getDeliveryFee(Integer amount) {
        var deliveryFee = deliveryFeeRepository.findByAmount(amount);

        if(deliveryFee == null) {
            throw new DeliveryFeeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        return deliveryFee;
    }

    @Override
    public void removeDeliveryFee(Integer amount) throws DeliveryFeeServiceException {
        var deliveryFee = deliveryFeeRepository.findByAmount(amount);

        if (deliveryFee != null) deliveryFeeRepository.delete(deliveryFee);
        else throw new DeliveryFeeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

    }

}
