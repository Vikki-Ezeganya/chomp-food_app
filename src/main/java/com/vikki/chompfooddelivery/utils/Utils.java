package com.vikki.chompfooddelivery.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new Random();
    private String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String generateUserId(int length) {
        return generateRandomString(length);
    }

    public String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for(int i = 0; i < length; i++) {
            returnValue.append(this.CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return returnValue.toString();
    }

    public String generateAddressId(int length) {
        return generateRandomString(length);
    }

    public String generateMenuItemId(int length) {
        return generateRandomString(length);
    }

    public String generateCartId(int length) {
       return generateRandomString(length);
    }

    public String generateCartItemId(int length) {
        return generateRandomString(length);
    }

}