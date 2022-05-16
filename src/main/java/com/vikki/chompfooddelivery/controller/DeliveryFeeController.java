package com.vikki.chompfooddelivery.controller;

import com.vikki.chompfooddelivery.dto.response.OperationStatusModel;
import com.vikki.chompfooddelivery.dto.response.RequestOperationName;
import com.vikki.chompfooddelivery.dto.response.RequestOperationStatus;
import com.vikki.chompfooddelivery.model.DeliveryFee;
import com.vikki.chompfooddelivery.service.DeliveryFeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/deliveryFee")
public class DeliveryFeeController {

    DeliveryFeeService deliveryFeeService;

    @GetMapping
    public List<DeliveryFee> getAllDeliveryAmount() {
        return deliveryFeeService.getAllDeliveryAmounts();
    }

    @PostMapping
    public ResponseEntity<String> addDeliveryFee(@RequestParam Integer amount){
        var deliveryFee = deliveryFeeService.createDeliveryFee(amount);
        return new ResponseEntity<>("Delivery amount: " + deliveryFee + " entered", HttpStatus.OK);
    }

    @GetMapping("/{amount}")
    public DeliveryFee getDeliveryFee(@PathVariable Integer amount) {
        return deliveryFeeService.getDeliveryFee(amount);
    }

    @DeleteMapping("/delete/{amount}")
    public ResponseEntity<OperationStatusModel> removeDeliveryFee(@PathVariable Integer amount){

        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
        operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());

        deliveryFeeService.removeDeliveryFee(amount);

        return new ResponseEntity<>(operationStatusModel, HttpStatus.OK);
    }
}
