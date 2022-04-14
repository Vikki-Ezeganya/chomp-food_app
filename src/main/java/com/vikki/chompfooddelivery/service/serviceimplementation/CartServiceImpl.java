package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.dto.CartItemDto;
import com.vikki.chompfooddelivery.model.CartItem;
import com.vikki.chompfooddelivery.model.MenuItem;
import com.vikki.chompfooddelivery.model.User;
import com.vikki.chompfooddelivery.repository.CartItemRepository;
import com.vikki.chompfooddelivery.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CartServiceImpl implements CartService {

    CartItemRepository cartRepository;

    @Override
    public Integer addMenuItemsToCart(CartItemDto cartItemDto) {
        Integer newQuantity = cartItemDto.getQuantity();

        User user = new User();
        user.setId(cartItemDto.getUserId());

        MenuItem menuItem = new MenuItem();
        menuItem.setId(cartItemDto.getMenuId());

        var cartItem = cartRepository.findByUserIdAndMenuItemId(cartItemDto.getUserId(),
                cartItemDto.getMenuId());
        CartItem cartItemToSave = new CartItem();

        if (cartItem != null) {
            newQuantity = cartItem.getQuantity() + cartItemDto.getQuantity();
            cartItem.setQuantity(newQuantity);
            cartRepository.save(cartItem);

        } else {
            cartItemToSave.setUser(user);
            cartItemToSave.setMenuItem(menuItem);
            cartItemToSave.setQuantity(cartItemDto.getQuantity());
            cartRepository.save(cartItemToSave);
            newQuantity = cartItemDto.getQuantity();
        }


        return newQuantity;
    }

}
