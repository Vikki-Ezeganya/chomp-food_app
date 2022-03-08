package com.vikki.chompfooddelivery.service;

import com.vikki.chompfooddelivery.dto.AddressDto;

public interface AddressDetailsService {
    AddressDto createAddress(AddressDto addressDto);
    AddressDto getAddress(String addressId );
    AddressDto editAddress(String addressId, AddressDto addressDto);
    void deleteAddress(String addressId);
}
