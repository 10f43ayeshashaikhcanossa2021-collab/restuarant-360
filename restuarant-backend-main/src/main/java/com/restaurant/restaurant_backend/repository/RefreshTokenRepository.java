package com.restaurant.restaurant_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant_backend.entity.RefreshToken;
import com.restaurant.restaurant_backend.entity.User;

public interface RefreshTokenRepository 
        extends JpaRepository<RefreshToken, Long> {


    Optional<RefreshToken> findByToken(String token);


    List<RefreshToken> findAllByUser(User user);

}