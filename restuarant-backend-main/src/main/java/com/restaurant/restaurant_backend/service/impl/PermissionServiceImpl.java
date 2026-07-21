package com.restaurant.restaurant_backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurant.restaurant_backend.dto.PermissionRequest;
import com.restaurant.restaurant_backend.entity.PermissionEntity;
import com.restaurant.restaurant_backend.repository.PermissionRepository;
import com.restaurant.restaurant_backend.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository repository;

    public PermissionServiceImpl(PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public PermissionEntity create(PermissionRequest request) {

        PermissionEntity entity = new PermissionEntity();

        entity.setName(request.getName());
        entity.setDescription(request.getDescription());

        return repository.save(entity);
    }

    @Override
    public List<PermissionEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public PermissionEntity get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}