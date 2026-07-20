package com.restaurant.restaurant_backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.restaurant.restaurant_backend.entity.PermissionEntity;
import com.restaurant.restaurant_backend.enums.Permission;
import com.restaurant.restaurant_backend.repository.PermissionRepository;

@Component
public class PermissionSeeder implements CommandLineRunner {

    private final PermissionRepository repository;

    public PermissionSeeder(PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {

        if(repository.count()==0){

            for(Permission permission : Permission.values()){

                PermissionEntity entity = new PermissionEntity();

                entity.setName(permission);

                repository.save(entity);

            }

            System.out.println("Permissions Seeded");

        }

    }
}