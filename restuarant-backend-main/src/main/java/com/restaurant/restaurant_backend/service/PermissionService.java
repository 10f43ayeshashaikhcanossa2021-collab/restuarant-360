package com.restaurant.restaurant_backend.service;

import java.util.List;

import com.restaurant.restaurant_backend.dto.PermissionRequest;
import com.restaurant.restaurant_backend.entity.PermissionEntity;

public interface PermissionService {

    PermissionEntity create(PermissionRequest request);

    List<PermissionEntity> getAll();

    PermissionEntity get(Long id);

    void delete(Long id);
}