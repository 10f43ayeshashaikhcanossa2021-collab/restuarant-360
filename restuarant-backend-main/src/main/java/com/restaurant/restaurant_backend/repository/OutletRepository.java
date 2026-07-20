package com.restaurant.restaurant_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant_backend.entity.Outlet;

public interface OutletRepository extends JpaRepository<Outlet, Long>{

    Optional<Outlet> findByName(String name);

}