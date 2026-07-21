package com.restaurant.restaurant_backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurant.restaurant_backend.entity.RefreshToken;
import com.restaurant.restaurant_backend.entity.User;
import com.restaurant.restaurant_backend.repository.RefreshTokenRepository;
import com.restaurant.restaurant_backend.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {


    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    public RefreshToken findByToken(String token){

        return refreshTokenRepository
                .findByToken(token)
                .orElseThrow(
                    () -> new RuntimeException("Refresh token not found")
                );
    }


    @Override
    public boolean isExpired(RefreshToken token){

        return token.getExpiryDate()
                .isBefore(java.time.LocalDateTime.now());
    }


    @Override
    public void revokeRefreshToken(String token){

        RefreshToken refreshToken = findByToken(token);

        refreshToken.setRevoked(true);

        refreshTokenRepository.save(refreshToken);
    }

@Override
public List<RefreshToken> getUserTokens(User user) {

    return refreshTokenRepository.findAllByUser(user);

}
@Override
public void revokeAllUserTokens(User user) {

    List<RefreshToken> tokens =
            refreshTokenRepository.findAllByUser(user);

    tokens.forEach(token -> {
        token.setRevoked(true);
    });

    refreshTokenRepository.saveAll(tokens);
}
@Override
public RefreshToken rotateRefreshToken(String token) {

    RefreshToken oldToken = findByToken(token);

    // revoke old refresh token
    oldToken.setRevoked(true);
    refreshTokenRepository.save(oldToken);


    User user = oldToken.getUser();


    // create new refresh token
    return createRefreshToken(user);
}
    // implement remaining methods
   @Override
public RefreshToken verifyRefreshToken(String token) {

    RefreshToken refreshToken = findByToken(token);

    if (Boolean.TRUE.equals(refreshToken.getRevoked())) {
        throw new RuntimeException("Refresh token revoked");
    }

    if (isExpired(refreshToken)) {
        throw new RuntimeException("Refresh token expired");
    }

    return refreshToken;
} 
@Override
public RefreshToken createRefreshToken(User user) {

    RefreshToken refreshToken = new RefreshToken();

    refreshToken.setUser(user);

    refreshToken.setToken(
            java.util.UUID.randomUUID().toString()
    );

    refreshToken.setExpiryDate(
            java.time.LocalDateTime.now()
                    .plusDays(7)
    );

    refreshToken.setRevoked(false);

    return refreshTokenRepository.save(refreshToken);
}
}