package com.restaurant.restaurant_backend.service;

import java.util.List;

import com.restaurant.restaurant_backend.entity.RefreshToken;
import com.restaurant.restaurant_backend.entity.User;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    RefreshToken findByToken(String token);

    boolean isExpired(RefreshToken token);

    RefreshToken verifyRefreshToken(String token);

    RefreshToken rotateRefreshToken(String token);

    void revokeRefreshToken(String token);

    void revokeAllUserTokens(User user);

    List<RefreshToken> getUserTokens(User user);
}