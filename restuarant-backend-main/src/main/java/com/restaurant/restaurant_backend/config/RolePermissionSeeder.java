package com.restaurant.restaurant_backend.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.restaurant.restaurant_backend.entity.PermissionEntity;
import com.restaurant.restaurant_backend.entity.RoleEntity;
import com.restaurant.restaurant_backend.enums.Permission;
import com.restaurant.restaurant_backend.enums.Role;
import com.restaurant.restaurant_backend.repository.PermissionRepository;
import com.restaurant.restaurant_backend.repository.RoleRepository;

@Component
public class RolePermissionSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RolePermissionSeeder(
            RoleRepository roleRepository,
            PermissionRepository permissionRepository) {

        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void run(String... args) {

        assignPermissions(Role.SUPER_ADMIN, Permission.values());

        assignPermissions(Role.OWNER,
                Permission.USER_CREATE,
                Permission.USER_UPDATE,
                Permission.USER_DELETE,

                Permission.MENU_CREATE,
                Permission.MENU_UPDATE,
                Permission.MENU_DELETE,

                Permission.ORDER_CREATE,
                Permission.ORDER_CANCEL,

                Permission.REPORT_VIEW,

                Permission.STOCK_UPDATE,

                Permission.KOT_UPDATE,

                Permission.PAYMENT_PROCESS);

        assignPermissions(Role.MANAGER,
                Permission.USER_CREATE,
                Permission.USER_UPDATE,

                Permission.MENU_CREATE,
                Permission.MENU_UPDATE,

                Permission.ORDER_CREATE,
                Permission.ORDER_CANCEL,

                Permission.REPORT_VIEW,

                Permission.STOCK_UPDATE,

                Permission.KOT_UPDATE);

        assignPermissions(Role.CASHIER,
                Permission.ORDER_CREATE,
                Permission.PAYMENT_PROCESS);

        assignPermissions(Role.CHEF,
                Permission.KOT_UPDATE);

        assignPermissions(Role.WAITER,
                Permission.ORDER_CREATE);

        assignPermissions(Role.ACCOUNTANT,
                Permission.REPORT_VIEW,
                Permission.PAYMENT_PROCESS);

        assignPermissions(Role.INVENTORY_MANAGER,
                Permission.STOCK_UPDATE);
    }

    private void assignPermissions(
            Role role,
            Permission... permissions) {

        RoleEntity roleEntity =
                roleRepository.findByName(role.name())
                        .orElseThrow();

        Set<PermissionEntity> permissionEntities =
                new HashSet<>();

        for (Permission permission : permissions) {

            PermissionEntity permissionEntity =
                    permissionRepository
                            .findByName(permission)
                            .orElseThrow();

            permissionEntities.add(permissionEntity);
        }

        roleEntity.setPermissions(permissionEntities);

        roleRepository.save(roleEntity);

        System.out.println(role.name() + " permissions assigned.");
    }
}