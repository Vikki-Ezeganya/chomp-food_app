package com.vikki.chompfooddelivery.exceptions;

public class CartServiceException extends RuntimeException{

    public CartServiceException(String message) {
        super(message);
    }
}
