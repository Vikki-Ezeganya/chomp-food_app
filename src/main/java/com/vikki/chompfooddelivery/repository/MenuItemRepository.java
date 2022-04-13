package com.vikki.chompfooddelivery.repository;

import com.vikki.chompfooddelivery.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    MenuItem findByName(String  menuItemName);
    MenuItem findByMenuId(Long menuItemId);
}
