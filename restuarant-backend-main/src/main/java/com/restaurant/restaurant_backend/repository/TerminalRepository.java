package com.restaurant.restaurant_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant_backend.entity.Terminal;

public interface TerminalRepository
        extends JpaRepository<Terminal, Long>{

    Optional<Terminal> findByName(String name);

}