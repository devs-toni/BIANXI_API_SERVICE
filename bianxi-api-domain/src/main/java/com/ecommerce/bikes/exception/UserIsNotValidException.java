package com.ecommerce.bikes.exception;

public class UserIsNotValidException extends RuntimeException {

    public UserIsNotValidException(String message) {
        super(message);
    }
}
