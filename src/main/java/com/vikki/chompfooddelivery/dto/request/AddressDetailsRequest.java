package com.vikki.chompfooddelivery.dto.request;

import lombok.Data;

@Data
public class AddressDetailsRequest {
    private Long userId;
    private String street;
    private String city;
    private String state;
    private String country;
}
