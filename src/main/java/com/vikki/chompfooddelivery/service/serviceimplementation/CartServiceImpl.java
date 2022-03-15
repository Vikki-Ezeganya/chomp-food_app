package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.dto.CartDto;
import com.vikki.chompfooddelivery.dto.CartItemDto;
import com.vikki.chompfooddelivery.model.Cart;
import com.vikki.chompfooddelivery.model.CartItem;
import com.vikki.chompfooddelivery.model.User;
import com.vikki.chompfooddelivery.repository.CartRepository;
import com.vikki.chompfooddelivery.repository.UserRepository;
import com.vikki.chompfooddelivery.service.CartService;
import com.vikki.chompfooddelivery.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CartServiceImpl implements CartService {

    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public CartDto createCart(CartDto cartDto) {

        int i = 0;
        for(CartItemDto cartItem: cartDto.getCartItems()) {

            cartItem.setCartItemId(new Utils().generateCartItemId(10));

            log.info("cart item::{}",cartItem);

//            cartItem.setCartDto(cartDto);
            cartDto.getCartItems().set(i, cartItem);

            i++;
        }

        //Get Cart
        log.info("cart dto::{}",cartDto);

        log.info("cart item dto get items::{}", cartDto.getCartItems());


        User user = userRepository.findByUserId(cartDto.getUserId());
        Cart cart = new Cart();

        cart.setCartId(new Utils().generateCartId(10));
        cart.setCartQuantity(cartDto.getCartQuantity());
        cart.setUser(user);
        cart.saveCartItems(cartDto.getCartItems());
        cart.setSubTotal(cartDto.getSubTotal());


        log.info("cart object new::{}", cart);
        log.info("cart items ::{}", cart.getCartItems());


        log.info("I am here");
        Cart savedCartItem = null;

        savedCartItem = cartRepository.save(cart);

        log.info("Saved cart item::{}", savedCartItem);

        CartDto returnValue = new CartDto();

        ModelMapper modelmapper = new ModelMapper();
        modelmapper.map(savedCartItem, returnValue );

        return  returnValue;
    }

}
