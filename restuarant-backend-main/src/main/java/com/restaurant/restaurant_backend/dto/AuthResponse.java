package com.restaurant.restaurant_backend.dto;


import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AuthResponse {


    private String accessToken;


    private String refreshToken;


    private UUID employeeId;


    private String employeeName;


    private String role;


}