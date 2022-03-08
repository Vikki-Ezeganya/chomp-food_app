package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.dto.UserDto;
import com.vikki.chompfooddelivery.exceptions.UserServiceException;
import com.vikki.chompfooddelivery.model.User;
import com.vikki.chompfooddelivery.repository.UserRepository;
import com.vikki.chompfooddelivery.service.UserService;
import com.vikki.chompfooddelivery.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserRepository userRepository;

    @Mock
    Utils utils;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserId("yy6kh");
        user.setFirstName("Vikki");
        user.setLastName("Ezeganya");
    }

    @Test
    void testGetUserByUserId() {

        when(userRepository.findByUserId(anyString())).thenReturn(user);

        UserDto userDto = userServiceImpl.getUserByUserId(anyString());

        assertNotNull(userDto);
        assertEquals("Vikki", userDto.getFirstName());

    }

    @Test
     void testGetUserByUserId_UserNameNotFoundException() {
        when(userRepository.findByUserId(anyString())).thenReturn(null);
        assertThrows(UsernameNotFoundException.class,
                () -> {
                    userServiceImpl.getUserByUserId(anyString());
                });
    }

    @Test
    void testCreateUser() {

        when(userRepository.findUserByEmail(anyString())).thenReturn(null);
        when(utils.generateUserId(ArgumentMatchers.anyInt())).thenReturn("fghj");
        when(bCryptPasswordEncoder.encode(ArgumentMatchers.anyString())).thenReturn("fghuub");
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);

        UserDto createdUser = userServiceImpl.createUser(userDto);
        assertNotNull(createdUser);
        assertEquals(user.getFirstName(), createdUser.getFirstName());

    }

    private String anyString() {
        return "yfguh";
    }


}