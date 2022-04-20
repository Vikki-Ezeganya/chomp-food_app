package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.dto.CartItemDto;
import com.vikki.chompfooddelivery.dto.response.ErrorMessages;
import com.vikki.chompfooddelivery.exceptions.CartServiceException;
import com.vikki.chompfooddelivery.model.CartItem;
import com.vikki.chompfooddelivery.model.MenuItem;
import com.vikki.chompfooddelivery.model.User;
import com.vikki.chompfooddelivery.repository.CartItemRepository;
import com.vikki.chompfooddelivery.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CartServiceImpl implements CartService {

    CartItemRepository cartRepository;

    @Override
    public List<CartItemDto> getAllCartItems() {
        var allCartItems = cartRepository.findAll();
        CartItemDto cartItemDto = new CartItemDto();

        List<CartItemDto> allCartItemsDto = new ArrayList<>();
        for(CartItem item : allCartItems) {
            cartItemDto.setMenuItem(item.getMenuItem());
            cartItemDto.setQuantity(item.getQuantity());
            allCartItemsDto.add(cartItemDto);
        }

        return allCartItemsDto;
    }

    @Override
    public CartItem getCartItemById(Long cartItemId) {
        var optionalCartItem = cartRepository.findById(cartItemId);
        CartItem cartItem = null;
        if(optionalCartItem.isPresent())
            cartItem = optionalCartItem.get();
        else throw new CartServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        return cartItem;
    }

    @Override
    public Integer addMenuItemsToCart(CartItemDto cartItemDto) {
        Integer newQuantity = cartItemDto.getQuantity();

        User user = new User();
        user.setId(cartItemDto.getUserId());

        MenuItem menuItem = new MenuItem();
        menuItem.setId(cartItemDto.getMenuItem().getId());

        var cartItem = cartRepository.findByUserIdAndMenuItemId(cartItemDto.getUserId(),
                cartItemDto.getMenuItem().getId());
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

    @Override
    public CartItemDto editCartItem(Long cartItemId, Integer quantity) throws CartServiceException {

        CartItem cartItem  = null;
        var optionalCartItem = cartRepository.findById(cartItemId);

        if (optionalCartItem.isPresent())
             cartItem = optionalCartItem.get();

        CartItemDto cartItemDto = null;
        if(cartItem != null) {
            var cartItemQuantity = cartItem.getQuantity();
            if(cartItem.getQuantity() > 1) cartItemQuantity -= 1;
            cartItem.setQuantity(cartItemQuantity);
            cartRepository.save(cartItem);
            ModelMapper modelMapper = new ModelMapper();
            cartItemDto = modelMapper.map(cartItem, CartItemDto.class);
        } else {
            throw new CartServiceException(ErrorMessages.NO_RECORD_FOUND.name());
        }

        return cartItemDto;
    }

    @Override
    public void deleteCartItem(Long cartItemId) throws CartServiceException {
        var optionalCartItem = cartRepository.findById(cartItemId);
        CartItem cartItemToDelete = null;
        if(optionalCartItem.isPresent()) {
            cartItemToDelete = optionalCartItem.get();
            cartRepository.delete(cartItemToDelete);
        } else {
            throw new CartServiceException(ErrorMessages.NO_RECORD_FOUND.name());
        }
    }

}
