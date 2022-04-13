package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.dto.CartItemDto;
import com.vikki.chompfooddelivery.exceptions.MenuItemServiceException;
import com.vikki.chompfooddelivery.exceptions.UserServiceException;
import com.vikki.chompfooddelivery.model.CartItem;
import com.vikki.chompfooddelivery.model.MenuItem;
import com.vikki.chompfooddelivery.model.User;
import com.vikki.chompfooddelivery.repository.CartItemRepository;
import com.vikki.chompfooddelivery.repository.MenuItemRepository;
import com.vikki.chompfooddelivery.repository.UserRepository;
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
    UserRepository userRepository;
    MenuItemRepository menuItemRepository;

    @Override
    public Integer addMenuItemsToCart(CartItemDto cartItemDto) {
        Integer newQuantity = cartItemDto.getQuantity();

        User user = null;
        MenuItem menuItem = null;

        var optionalUser = userRepository.findById(cartItemDto.getUserId());
        if(optionalUser.isPresent()) user = optionalUser.get();

        var optionalMenuItem = menuItemRepository.findById(cartItemDto.getMenuId());
        if(optionalMenuItem.isPresent())  menuItem = optionalMenuItem.get();

        var cartItem = cartRepository.findByUserIdAndMenuItemId(cartItemDto.getUserId(),
                cartItemDto.getMenuId());
        CartItem cartItemToSave = new CartItem();

        if (cartItem != null) {
            newQuantity = cartItem.getQuantity() + cartItemDto.getQuantity();
        } else {
            cartItemToSave.setUser(user);
            cartItemToSave.setMenuItem(menuItem);
        }

        cartItemToSave.setQuantity(newQuantity);
        cartRepository.save(cartItemToSave);

        return newQuantity;
    }

}
