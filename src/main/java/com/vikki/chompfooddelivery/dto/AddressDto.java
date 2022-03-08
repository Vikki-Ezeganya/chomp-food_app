package com.vikki.chompfooddelivery.dto;

import lombok.Data;

@Data
public class AddressDto {

    private String userId;
    private String addressId;
    private String street;
    private String city;
    private String state;
    private String country;
//    private UserDto user;
}
