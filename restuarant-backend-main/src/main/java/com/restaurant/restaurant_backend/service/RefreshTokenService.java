package com.restaurant.restaurant_backend.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.restaurant.restaurant_backend.entity.RefreshToken;
import com.restaurant.restaurant_backend.entity.User;
import com.restaurant.restaurant_backend.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    // Create Refresh Token
    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(7));
        refreshToken.setRevoked(false);

        return refreshTokenRepository.save(refreshToken);
    }

    // Find Token
    public RefreshToken findByToken(String token) {

        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh Token not found"));
    }

    // Check Expiry
    public boolean isExpired(RefreshToken token) {

        return token.getExpiryDate().isBefore(LocalDateTime.now());
    }

    // Revoke Token
    public void revokeToken(RefreshToken token) {

        token.setRevoked(true);
        refreshTokenRepository.save(token);
    }
    public void revokeRefreshToken(String token) {

    refreshTokenRepository.findByToken(token)
            .ifPresent(refreshToken -> {
                refreshTokenRepository.delete(refreshToken);
            });
}
}