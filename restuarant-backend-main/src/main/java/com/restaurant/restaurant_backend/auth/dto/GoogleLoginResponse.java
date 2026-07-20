package com.restaurant.restaurant_backend.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleLoginResponse {

    private String accessToken;

    private String refreshToken;

}