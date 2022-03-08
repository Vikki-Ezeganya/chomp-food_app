package com.vikki.chompfooddelivery.service;

import com.vikki.chompfooddelivery.dto.MenuItemDto;

import java.util.List;

public interface MenuItemService {
    MenuItemDto createMenuItem(MenuItemDto menuItemDto);
    MenuItemDto updateMenuItem(String menuId, MenuItemDto menuItemDto);
    void deleteMenuItem(String menuid);
    List<MenuItemDto> getAllMenuItems(int page, int limit);
    MenuItemDto getMenuItem(String menuItemId);

}
