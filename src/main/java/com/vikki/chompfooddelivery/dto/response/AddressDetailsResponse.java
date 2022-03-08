package com.vikki.chompfooddelivery.dto.response;

import lombok.Data;

@Data
public class AddressDetailsResponse {
    private String addressId;
    private String street;
    private String city;
    private String state;
    private String country;
}
