package com.vikki.chompfooddelivery.repository;

import com.vikki.chompfooddelivery.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
    CartItem findByUserIdAndMenuItemId(Long userId, Long menuItemId);
//    void updateCartItemQuantity(Integer quantity, MenuItem menuItem, User user);
//    void deleteCartItemByUserAndMenuItem(User user, MenuItem menuItem);
}

