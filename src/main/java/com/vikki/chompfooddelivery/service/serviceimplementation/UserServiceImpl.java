package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.dto.UserDto;
import com.vikki.chompfooddelivery.dto.response.ErrorMessages;
import com.vikki.chompfooddelivery.enums.UserRole;
import com.vikki.chompfooddelivery.exceptions.UserServiceException;
import com.vikki.chompfooddelivery.model.User;
import com.vikki.chompfooddelivery.repository.UserRepository;
import com.vikki.chompfooddelivery.service.UserService;
import com.vikki.chompfooddelivery.utils.Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    Utils utils;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {

        if(userRepository.findUserByEmail(userDto.getEmail()) != null )
            throw new UserServiceException("Record already exists!");

        ModelMapper modelmapper = new ModelMapper();
        User user = modelmapper.map(userDto, User.class);

        String userId = utils.generateUserId(10);
        user.setUserId(userId);
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setRole(UserRole.USER);

        User savedUser =  userRepository.save(user);

        return modelmapper.map(savedUser, UserDto.class);

    }

    @Override
    public UserDto getUser(String email) {
        if (userRepository.findUserByEmail(email) == null) throw new UsernameNotFoundException("user not found!");
        var user = userRepository.findUserByEmail(email);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);

        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserDto userDto = new UserDto();

        User user = userRepository.findByUserId(userId);
        if(user == null)  throw new UsernameNotFoundException(userId);
        BeanUtils.copyProperties(user, userDto);

        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        UserDto returnValue = new UserDto();

        User user = userRepository.findByUserId(userId);
        if(user == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        User updatedUser = userRepository.save(user);

        BeanUtils.copyProperties(updatedUser, returnValue);
        return returnValue;
    }

    @Override
    public void deleteUser(String userId) {
        User foundUser = userRepository.findByUserId(userId);
        if(foundUser == null ) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        userRepository.delete(foundUser);

    }

    @Override
    public List<UserDto> getAllUsers(int page, int limit) {
        List<UserDto> listOfUsers = new ArrayList<>();

        PageRequest pageRequest =  PageRequest.of(page,limit);


        Page<User> usersPage = userRepository.findAll(pageRequest);
        var users = usersPage.getContent();

        for(User user: users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            listOfUsers.add(userDto);
        }

        return listOfUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findUserByEmail(email);

        if(user == null) throw new UsernameNotFoundException(email);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(), new ArrayList<>());
    }
}
