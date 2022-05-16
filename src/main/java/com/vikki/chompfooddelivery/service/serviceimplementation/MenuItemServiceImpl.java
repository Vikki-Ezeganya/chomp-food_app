package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.dto.MenuItemDto;
import com.vikki.chompfooddelivery.dto.request.MenuItemRequest;
import com.vikki.chompfooddelivery.dto.response.ErrorMessages;
import com.vikki.chompfooddelivery.dto.response.OperationStatusModel;
import com.vikki.chompfooddelivery.exceptions.MenuItemServiceException;
import com.vikki.chompfooddelivery.model.MenuItem;
import com.vikki.chompfooddelivery.repository.MenuItemRepository;
import com.vikki.chompfooddelivery.service.MenuItemService;
import com.vikki.chompfooddelivery.utils.Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MenuItemServiceImpl implements MenuItemService {

    MenuItemRepository menuItemRepository;

    @Override
    public MenuItemDto createMenuItem(MenuItemDto menuItemDto) throws MenuItemServiceException {

        if(menuItemRepository.findByName(menuItemDto.getName()) != null)
            throw new MenuItemServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());


        ModelMapper modelmapper = new ModelMapper();
        MenuItem menuitem = modelmapper.map(menuItemDto, MenuItem.class);
        menuitem.setMenuId(new Utils().generateMenuItemId(10));
        menuitem.setDateCreated(new Date());

        var savedMenuItem = menuItemRepository.save(menuitem);

        return modelmapper.map(savedMenuItem, MenuItemDto.class);
    }

    @Override
    public MenuItemDto updateMenuItem(Long menuId, MenuItemDto menuItemDto) throws MenuItemServiceException{
        var menuItem = menuItemRepository.findByMenuId(menuId);
        if (menuItem == null)
            throw new MenuItemServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        menuItem.setName(menuItemDto.getName());
        menuItem.setPrice(menuItemDto.getPrice());
        menuItem.setDescription(menuItemDto.getDescription());
        menuItem.setImage(menuItemDto.getImage());

        var updatedMenuItem = menuItemRepository.save(menuItem);

        ModelMapper modelmapper = new ModelMapper();
        return modelmapper.map(updatedMenuItem, MenuItemDto.class);

    }

    @Override
    public List<MenuItemDto> getAllMenuItems(int page , int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit);

        var pageOfMenuItems = menuItemRepository.findAll(pageRequest);
        var listOfMenuItems = pageOfMenuItems.getContent();

        List<MenuItemDto> allMenuItemsDto = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for(MenuItem item: listOfMenuItems) {
            MenuItemDto menuItemDto = modelMapper.map(item, MenuItemDto.class);
            allMenuItemsDto.add(menuItemDto);
        }

        return allMenuItemsDto;
    }

    @Override
    public MenuItemDto getMenuItem(Long menuItemId) throws MenuItemServiceException{

        var foundMenuItem = menuItemRepository.findByMenuId( menuItemId);
        if(foundMenuItem == null) throw new MenuItemServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        ModelMapper modelmapper = new ModelMapper();

        return modelmapper.map(foundMenuItem, MenuItemDto.class);

    }

    @Override
    public void deleteMenuItem(Long menuItemId) throws MenuItemServiceException{

        if (menuItemRepository.findByMenuId(menuItemId) == null)
            throw new MenuItemServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        MenuItem menuItemToDelete = menuItemRepository.findByMenuId(menuItemId);
        menuItemRepository.delete(menuItemToDelete);
    }


}
