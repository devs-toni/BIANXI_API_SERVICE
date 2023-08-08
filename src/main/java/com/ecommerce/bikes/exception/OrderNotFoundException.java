package com.ecommerce.bikes.exception;

import ch.qos.logback.core.encoder.EchoEncoder;

public class OrderNotFoundException extends Exception {

    public OrderNotFoundException(String message) {
        super(message);
    }
}
