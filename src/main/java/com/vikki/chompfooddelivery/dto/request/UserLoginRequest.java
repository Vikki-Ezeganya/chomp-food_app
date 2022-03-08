package com.vikki.chompfooddelivery.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginRequest {
    private String email;
    private String password;
}
