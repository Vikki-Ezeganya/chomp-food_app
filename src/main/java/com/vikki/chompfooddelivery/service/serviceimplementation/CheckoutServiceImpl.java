package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.dto.AddressDto;
import com.vikki.chompfooddelivery.dto.UserDto;
import com.vikki.chompfooddelivery.model.CheckoutInfo;
import com.vikki.chompfooddelivery.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CheckoutServiceImpl implements CheckoutService {

    private CartServiceImpl cartService;
    private DeliveryFeeServiceImpl deliveryFeeService;
    private UserServiceImpl userService;
    private AddressDetailsServiceImpl addressService;

    @Override
    public CheckoutInfo prepareCheckoutInfo() {

        Integer subtotal = cartService.getCartSubTotal();
        Integer deliveryFee = deliveryFeeService.getDeliveryFee(1000).getAmount();
        var cartItems = cartService.getAllCartItems();

        var userId = getUser().getId();
        var address = getDeliveryAddress(userId);

        String nameOfUser = getUser().getFirstName() + getUser().getLastName();
        var phoneNumber = getUser().getPhoneNumber();

        CheckoutInfo checkoutInfo = new CheckoutInfo();
        checkoutInfo.setNameOfUser(nameOfUser);
        checkoutInfo.setPhoneNumber(phoneNumber);
        checkoutInfo.setDeliveryAddress(address);
        checkoutInfo.setDeliveryFee(deliveryFee);
        checkoutInfo.setQuantity(cartService.getNumberOfCartItems());
        checkoutInfo.setSubtotal(subtotal);
        checkoutInfo.setTotal(getTotal(subtotal, deliveryFee));
        checkoutInfo.setDeliveryDate(LocalDate.now());
        checkoutInfo.setCartItems(cartItems);

        return checkoutInfo;
    }

    private Integer getTotal(Integer subtotal, Integer deliveryFee) {
        return subtotal + deliveryFee;
    }

    private AddressDto getDeliveryAddress(Long userId) {
        return addressService.getAddress(userId);
    }

    private UserDto getUser() {
        String username = null;
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userService.getUser(username);
    }

}