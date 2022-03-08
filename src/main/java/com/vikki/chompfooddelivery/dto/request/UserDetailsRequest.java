package com.vikki.chompfooddelivery.dto.request;

import lombok.Data;

@Data
public class UserDetailsRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
}
