package com.restaurant.restaurant_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurant_backend.dto.PermissionRequest;
import com.restaurant.restaurant_backend.entity.PermissionEntity;
import com.restaurant.restaurant_backend.service.PermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @PostMapping
    public PermissionEntity create(@RequestBody PermissionRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<PermissionEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PermissionEntity get(@PathVariable Long id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}