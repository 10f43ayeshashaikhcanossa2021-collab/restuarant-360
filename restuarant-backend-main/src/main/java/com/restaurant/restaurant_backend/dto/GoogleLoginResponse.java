package com.restaurant.restaurant_backend.dto;

import lombok.Data;

@Data
public class GoogleLoginResponse {

    private String accessToken;

    private String refreshToken;

    private String fullName;

    private String email;

    private String role;

    private String message;

}