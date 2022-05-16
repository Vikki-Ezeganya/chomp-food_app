package com.vikki.chompfooddelivery.controller;

import com.vikki.chompfooddelivery.model.CheckoutInfo;
import com.vikki.chompfooddelivery.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class CheckOutController {

    CheckoutService checkoutService;

    @GetMapping
    public ResponseEntity<CheckoutInfo> displayCheckOutInfo () {
        var checkoutInfo = checkoutService.prepareCheckoutInfo();
        return new ResponseEntity<> (checkoutInfo, HttpStatus.OK);
    }

}
