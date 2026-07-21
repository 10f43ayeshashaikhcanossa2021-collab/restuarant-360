package com.restaurant.restaurant_backend.service.impl;

import com.restaurant.restaurant_backend.dto.GoogleLoginRequest;
import com.restaurant.restaurant_backend.dto.GoogleLoginResponse;
import com.restaurant.restaurant_backend.dto.LoginRequest;
import com.restaurant.restaurant_backend.dto.LogoutRequest;
import com.restaurant.restaurant_backend.dto.PinLoginRequest;
import com.restaurant.restaurant_backend.dto.RefreshRequest;
import com.restaurant.restaurant_backend.dto.RefreshResponse;
import com.restaurant.restaurant_backend.dto.RegisterRequest;
import com.restaurant.restaurant_backend.dto.UserResponse;

public interface AuthenticationServiceImpl{

    String authenticate(LoginRequest request);

    RefreshResponse authenticateWithRefresh(LoginRequest request);

    RefreshResponse loginWithPin(PinLoginRequest request);

    String register(RegisterRequest request);

    RefreshResponse refreshToken(RefreshRequest request);

    void logout(LogoutRequest request);

    UserResponse getCurrentUser(String email);

    void changePassword(
            String email,
            String oldPassword,
            String newPassword
    );

    GoogleLoginResponse googleLogin(
            GoogleLoginRequest request
    ) throws Exception;

}