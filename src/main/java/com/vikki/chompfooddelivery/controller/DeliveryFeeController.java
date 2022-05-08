package com.vikki.chompfooddelivery.controller;

import com.vikki.chompfooddelivery.service.DeliveryFeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/deliveryFee")
public class DeliveryFeeController {

    DeliveryFeeService deliveryFeeService;

    @PostMapping
    public ResponseEntity<String> addDeliveryFee(@RequestParam Integer amount){
        deliveryFeeService.createDeliveryFee(amount);
        return new ResponseEntity<>("Delivery amount: " + amount + " entered", HttpStatus.OK);
    }
}
