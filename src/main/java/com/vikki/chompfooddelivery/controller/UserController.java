package com.vikki.chompfooddelivery.controller;


import com.vikki.chompfooddelivery.dto.UserDto;
import com.vikki.chompfooddelivery.dto.request.UserDetailsRequest;
import com.vikki.chompfooddelivery.dto.response.*;
import com.vikki.chompfooddelivery.exceptions.UserServiceException;
import com.vikki.chompfooddelivery.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDetailsResponse> getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List <UserDetailsResponse> listOfUsers = new ArrayList<>();

        List<UserDto>  gottenUsers = userService.getAllUsers(page, limit);
         for (UserDto user: gottenUsers) {
             UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
             BeanUtils.copyProperties(user, userDetailsResponse);
             listOfUsers.add(userDetailsResponse);
         }

        return listOfUsers;
    }

    @GetMapping(path = "/{userId}")
    public UserDetailsResponse getUser(@PathVariable String userId) {
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
        UserDto userDto = userService.getUserByUserId(userId);
        BeanUtils.copyProperties(userDto, userDetailsResponse);

        return userDetailsResponse;
   }

    @PostMapping
    public UserDetailsResponse createUser(@RequestBody UserDetailsRequest userDetailsRequest) throws Exception {

        if(userDetailsRequest.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(userDetailsRequest, UserDto.class);

        UserDto createdUser = userService.createUser(userDto);
        UserDetailsResponse userDetailsResponse = modelMapper.map(createdUser, UserDetailsResponse.class);

        return userDetailsResponse;
    }

    @PutMapping(path = "/{userId}")
    public UserDetailsResponse updateUser(@PathVariable String userId, @RequestBody UserDetailsRequest request) {
        UserDetailsResponse userDetailsResponse  = new UserDetailsResponse();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(request, userDto);
        UserDto updatedUser = userService.updateUser(userId, userDto);
        BeanUtils.copyProperties(updatedUser, userDetailsResponse);

        return userDetailsResponse;
    }

    @DeleteMapping(path = "/{userId}")
    public OperationStatusModel deleteUser(@PathVariable String userId) {
        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
        operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
        userService.deleteUser(userId);

        return operationStatusModel;
    }
}
