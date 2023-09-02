package com.ecommerce.bikes.exception;

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
