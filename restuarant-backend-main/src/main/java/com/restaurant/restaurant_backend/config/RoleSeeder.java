package com.restaurant.restaurant_backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.restaurant.restaurant_backend.entity.RoleEntity;
import com.restaurant.restaurant_backend.enums.Role;
import com.restaurant.restaurant_backend.repository.RoleRepository;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {

        for (Role role : Role.values()) {

            if (roleRepository.findByName(role.name()).isEmpty()) {

                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setName(role.name());

                roleRepository.save(roleEntity);

                System.out.println("Inserted Role: " + role.name());
            }
        }
    }
}