package com.restaurant.restaurant_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant_backend.entity.Branch;
import com.restaurant.restaurant_backend.entity.Category;
import com.restaurant.restaurant_backend.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByCategory(Category category);

    List<MenuItem> findByBranch(Branch branch);

    List<MenuItem> findByAvailableTrue();

    boolean existsByName(String name);

}