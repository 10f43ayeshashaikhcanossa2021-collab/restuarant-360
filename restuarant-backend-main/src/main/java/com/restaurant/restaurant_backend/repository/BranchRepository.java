package com.restaurant.restaurant_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant_backend.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Optional<Branch> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByName(String name);
}