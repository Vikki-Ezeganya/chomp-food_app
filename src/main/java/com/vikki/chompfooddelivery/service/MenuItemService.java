package com.vikki.chompfooddelivery.service;

import com.vikki.chompfooddelivery.dto.MenuItemDto;

import java.util.List;

public interface MenuItemService {
    MenuItemDto createMenuItem(MenuItemDto menuItemDto);
    MenuItemDto updateMenuItem(Long menuItemId, MenuItemDto menuItemDto);
    void deleteMenuItem(Long menuid);
    List<MenuItemDto> getAllMenuItems(int page, int limit);
    MenuItemDto getMenuItem(Long menuItemId);

}
