package com.vikki.chompfooddelivery.service.serviceimplementation;

import com.vikki.chompfooddelivery.dto.AddressDto;
import com.vikki.chompfooddelivery.exceptions.AddressDetailsServiceException;
import com.vikki.chompfooddelivery.model.Address;
import com.vikki.chompfooddelivery.model.User;
import com.vikki.chompfooddelivery.repository.AddressDetailsRepository;
import com.vikki.chompfooddelivery.service.AddressDetailsService;
import com.vikki.chompfooddelivery.utils.Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressDetailsServiceImpl implements AddressDetailsService {

    AddressDetailsRepository addressDetailsRepository;


    @Override
    public AddressDto createAddress(AddressDto addressDto) {

        ModelMapper modelMapper = new ModelMapper();

        Address addressToSave = new Address();

        User user = new User();
        user.setId(addressDto.getUserId());

        addressToSave.setUser(user);
        addressToSave.setStreet(addressDto.getStreet());
        addressToSave.setCity(addressDto.getCity());
        addressToSave.setState(addressDto.getState());
        addressToSave.setCountry(addressDto.getCountry());

        addressToSave.setAddressId(new Utils().generateAddressId(10));

        Address savedAddress = addressDetailsRepository.save(addressToSave);

        return modelMapper.map(savedAddress, AddressDto.class);
    }

    @Override
    public AddressDto getAddress(Long userId) throws AddressDetailsServiceException {
        Address address = addressDetailsRepository.findByUserId(userId);
        if (address == null) throw new AddressDetailsServiceException("Address does not exist");

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    public AddressDto editAddress(String addressId, AddressDto addressDto) throws AddressDetailsServiceException {
        Address address = addressDetailsRepository.findByAddressId(addressId);
        if ( address == null)
            throw new AddressDetailsServiceException("Address does not exist");

        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());

        Address savedAddress = addressDetailsRepository.save(address);

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(savedAddress, AddressDto.class);
    }

    @Override
    public void deleteAddress(String addressId) throws AddressDetailsServiceException{
        Address address = addressDetailsRepository.findByAddressId(addressId);
        if(address == null) throw new AddressDetailsServiceException("address does not exist");

        addressDetailsRepository.delete(address);
    }

}