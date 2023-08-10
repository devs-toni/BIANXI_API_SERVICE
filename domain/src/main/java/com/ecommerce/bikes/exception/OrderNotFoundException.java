package com.ecommerce.bikes.exception;

import ch.qos.logback.core.encoder.EchoEncoder;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }
}
