package com.restaurant.restaurant_backend.dto;

public class GoogleLoginRequest {

    private String token;

    public GoogleLoginRequest() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}