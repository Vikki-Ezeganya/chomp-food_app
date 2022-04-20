package com.vikki.chompfooddelivery.controller;

import com.vikki.chompfooddelivery.dto.MenuItemDto;
import com.vikki.chompfooddelivery.dto.request.MenuItemRequest;
import com.vikki.chompfooddelivery.dto.response.MenuItemResponse;
import com.vikki.chompfooddelivery.dto.response.OperationStatusModel;
import com.vikki.chompfooddelivery.dto.response.RequestOperationName;
import com.vikki.chompfooddelivery.dto.response.RequestOperationStatus;
import com.vikki.chompfooddelivery.service.MenuItemService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("menuItems")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MenuItemController {

    MenuItemService menuItemService;

    @GetMapping
    public List<MenuItemResponse> getAllMenuItems
            (@RequestParam(value = "page", defaultValue = "0") int page,
             @RequestParam(value = "limit", defaultValue = "5" ) int limit ) {
        var listOfMenuItem = menuItemService.getAllMenuItems(page, limit);
        List<MenuItemResponse> listOfMenuItemResponse = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        for(MenuItemDto item: listOfMenuItem) {
            var menuItemResponse = modelMapper.map(item, MenuItemResponse.class);
            listOfMenuItemResponse.add(menuItemResponse);
        }

        return listOfMenuItemResponse;
    }

    @GetMapping("/{menuItemId}")
    public MenuItemResponse getMenuItem(@PathVariable Long menuItemId) {

        MenuItemDto gottenMenuItem = menuItemService.getMenuItem(menuItemId);
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(gottenMenuItem, MenuItemResponse.class);
    }

    @PostMapping
    public MenuItemResponse createMenuItem(@RequestBody MenuItemRequest request) {

        ModelMapper modelMapper = new ModelMapper();
        MenuItemDto  menuItemDto = modelMapper.map(request, MenuItemDto.class);

        MenuItemDto createdMenuItem = menuItemService.createMenuItem(menuItemDto);

        return modelMapper.map(createdMenuItem, MenuItemResponse.class);
    }

    @PutMapping(path = "/{menuId}")
    public MenuItemResponse updateMenuItem(@PathVariable Long menuId, @RequestBody MenuItemRequest request) {

        ModelMapper modelMapper = new ModelMapper();
        MenuItemDto menuItemDto = modelMapper.map(request, MenuItemDto.class);

        MenuItemDto updatedMenuItem = menuItemService.updateMenuItem(menuId, menuItemDto);

        return modelMapper.map(updatedMenuItem, MenuItemResponse.class);
    }

    @DeleteMapping(path = "/{menuId}")
    public OperationStatusModel deleteMenuItem(@PathVariable Long menuId) {

        OperationStatusModel model = new OperationStatusModel();
        model.setOperationName(RequestOperationName.DELETE.name());
        model.setOperationResult(RequestOperationStatus.SUCCESS.name());

        menuItemService.deleteMenuItem(menuId);
        return model;

    }

}
