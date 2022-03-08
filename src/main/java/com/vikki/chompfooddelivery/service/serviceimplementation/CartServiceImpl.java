package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.dto.CartDto;
import com.vikki.chompfooddelivery.dto.response.ErrorMessage;
import com.vikki.chompfooddelivery.dto.response.ErrorMessages;
import com.vikki.chompfooddelivery.exceptions.CartServiceException;
import com.vikki.chompfooddelivery.model.Cart;
import com.vikki.chompfooddelivery.repository.CartRepository;
import com.vikki.chompfooddelivery.service.CartService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CartServiceImpl implements CartService {

    CartRepository cartRepository;

    @Override
    public CartDto createCartItem(CartDto cartDto) {

//        if(cartRepository.() != null ) throw new CartServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        Cart cartItems = new Cart();
        ModelMapper modelmapper = new ModelMapper();
        modelmapper.map(cartDto, cartItems);


        Cart savedCartItem = cartRepository.save(cartItems);
        CartDto returnValue = new CartDto();

        modelmapper.map(savedCartItem, returnValue );

        return  returnValue;
    }
}
