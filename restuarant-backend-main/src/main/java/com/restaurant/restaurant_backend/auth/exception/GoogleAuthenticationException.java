package com.restaurant.restaurant_backend.auth.exception;

public class GoogleAuthenticationException extends RuntimeException {

    public GoogleAuthenticationException(String message) {
        super(message);
    }

}