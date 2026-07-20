package com.restaurant.restaurant_backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.restaurant.restaurant_backend.entity.PermissionEntity;
import com.restaurant.restaurant_backend.enums.Permission;
import com.restaurant.restaurant_backend.repository.PermissionRepository;

@Component
public class PermissionSeeder implements CommandLineRunner {

    private final PermissionRepository permissionRepository;

    public PermissionSeeder(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void run(String... args) {

        if (permissionRepository.count() > 0) {
            System.out.println("Permissions already exist.");
            return;
        }

        for (Permission permission : Permission.values()) {

            PermissionEntity entity = new PermissionEntity();
            entity.setName(permission);

            permissionRepository.save(entity);

            System.out.println("Inserted Permission : " + permission);
        }

        System.out.println("Permissions Seeded Successfully");
    }
}