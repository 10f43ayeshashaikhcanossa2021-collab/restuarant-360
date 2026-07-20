package com.restaurant.restaurant_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.restaurant_backend.entity.RefreshToken;
import com.restaurant.restaurant_backend.entity.User;
import com.restaurant.restaurant_backend.repository.RefreshTokenRepository;

@Service
@Transactional
public class RefreshTokenService {

    private static final long REFRESH_TOKEN_EXPIRY_DAYS = 7;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    /**
     * Create Refresh Token
     */
    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);

        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken.setExpiryDate(
                LocalDateTime.now().plusDays(REFRESH_TOKEN_EXPIRY_DAYS));

        refreshToken.setRevoked(false);

        refreshToken.setCreatedAt(LocalDateTime.now());

        return refreshTokenRepository.save(refreshToken);
    }

    /**
     * Validate Refresh Token
     */
    public RefreshToken verifyRefreshToken(String token) {

        RefreshToken refreshToken =
                refreshTokenRepository.findByToken(token)
                        .orElseThrow(() ->
                                new RuntimeException("Refresh Token Not Found"));

        if (Boolean.TRUE.equals(refreshToken.getRevoked())) {
            throw new RuntimeException("Refresh Token Revoked");
        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh Token Expired");
        }

        return refreshToken;
    }

    /**
     * Revoke Token
     */
    public void revokeToken(String token) {

        RefreshToken refreshToken =
                verifyRefreshToken(token);

        refreshToken.setRevoked(true);

        refreshTokenRepository.save(refreshToken);
    }

    /**
     * Delete All Tokens
     */
    public void deleteUserTokens(User user) {

        refreshTokenRepository.deleteByUser(user);
    }

    /**
     * Active Tokens
     */
    public List<RefreshToken> getActiveTokens(User user) {

        return refreshTokenRepository.findByUserAndRevokedFalse(user);
    }

    /**
     * Rotate Refresh Token
     */
    public RefreshToken rotateRefreshToken(String token) {

        RefreshToken oldToken =
                verifyRefreshToken(token);

        oldToken.setRevoked(true);

        refreshTokenRepository.save(oldToken);

        return createRefreshToken(oldToken.getUser());
    }
    // ===============================
// Compatibility Methods
// ===============================

public RefreshToken findByToken(String token) {
    return refreshTokenRepository.findByToken(token)
            .orElseThrow(() ->
                    new RuntimeException("Refresh Token Not Found"));
}

public boolean isExpired(RefreshToken token) {
    return token.getExpiryDate().isBefore(LocalDateTime.now());
}

public void revokeRefreshToken(String token) {

    RefreshToken refreshToken = findByToken(token);

    refreshToken.setRevoked(true);

    refreshTokenRepository.save(refreshToken);
}

}