package com.vikki.chompfooddelivery.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class UserDto {

    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;
    private String encryptedpassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus = false;

}
