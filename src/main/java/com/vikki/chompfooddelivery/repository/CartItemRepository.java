package com.vikki.chompfooddelivery.repository;

import com.vikki.chompfooddelivery.model.CartItem;
import com.vikki.chompfooddelivery.model.MenuItem;
import com.vikki.chompfooddelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
    CartItem findByUserIdAndMenuItemId(Long userId, Long menuItemId);
//    CartItem findCartItemById(Long id);
//    CartItem findById(Long id);
//    void deleteCartItemByUserAndMenuItem(User user, MenuItem menuItem);
}

