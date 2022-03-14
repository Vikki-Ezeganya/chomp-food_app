package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.dto.CartDto;
import com.vikki.chompfooddelivery.dto.CartItemDto;
import com.vikki.chompfooddelivery.model.Cart;
import com.vikki.chompfooddelivery.repository.CartRepository;
import com.vikki.chompfooddelivery.service.CartService;
import com.vikki.chompfooddelivery.utils.Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CartServiceImpl implements CartService {

    CartRepository cartRepository;

    @Override
    public CartDto createCart(CartDto cartDto) {

        int i = 0;
        for(CartItemDto cartItem: cartDto.getCartItems()) {
            cartItem.setCartItemId(new Utils().generateCartItemId(10));
            cartItem.setCartDto(cartDto);
            cartDto.getCartItems().set(i, cartItem);
            i++;
        }

        Cart cart = new Cart();

        ModelMapper modelmapper = new ModelMapper();
        modelmapper.map(cartDto, cart);
        cart.setCartId(new Utils().generateCartId(10));

        Cart savedCartItem = cartRepository.save(cart);
        CartDto returnValue = new CartDto();


        modelmapper.map(savedCartItem, returnValue );

        return  returnValue;
    }
}
