package com.ecommerce.bikes.exception;

    public class LikeDoesNotExistResultException extends RuntimeException {

        public LikeDoesNotExistResultException(String message) {
            super(message);
        }
}
