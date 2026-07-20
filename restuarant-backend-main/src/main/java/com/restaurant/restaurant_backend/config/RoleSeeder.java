package com.restaurant.restaurant_backend.config;

import java.util.Arrays;
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
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleSeeder(
            RoleRepository roleRepository,
            PermissionRepository permissionRepository) {

        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void run(String... args) {

        // SUPER ADMIN
        createOrUpdateRole(
                Role.SUPER_ADMIN,
                Permission.values()
        );

        // OWNER
        createOrUpdateRole(
                Role.OWNER,

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

                Permission.PAYMENT_PROCESS,

                Permission.OUTLET_CREATE,
                Permission.OUTLET_VIEW,

                Permission.TERMINAL_CREATE,
                Permission.TERMINAL_VIEW,
                Permission.TERMINAL_DELETE
        );

        // MANAGER
        createOrUpdateRole(
                Role.MANAGER,

                Permission.USER_UPDATE,

                Permission.MENU_CREATE,
                Permission.MENU_UPDATE,

                Permission.ORDER_CREATE,
                Permission.ORDER_CANCEL,

                Permission.REPORT_VIEW,

                Permission.STOCK_UPDATE,

                Permission.KOT_UPDATE,

                Permission.OUTLET_VIEW,

                Permission.TERMINAL_VIEW
        );

        // ACCOUNTANT
        createOrUpdateRole(
                Role.ACCOUNTANT,

                Permission.REPORT_VIEW,

                Permission.PAYMENT_PROCESS
        );

        // CASHIER
        createOrUpdateRole(
                Role.CASHIER,

                Permission.ORDER_CREATE,

                Permission.PAYMENT_PROCESS
        );

        // WAITER
        createOrUpdateRole(
                Role.WAITER,

                Permission.ORDER_CREATE
        );

        // CHEF
        createOrUpdateRole(
                Role.CHEF,

                Permission.KOT_UPDATE
        );

        // INVENTORY MANAGER
        createOrUpdateRole(
                Role.INVENTORY_MANAGER,

                Permission.STOCK_UPDATE,

                Permission.REPORT_VIEW
        );

        System.out.println("==================================");
        System.out.println("Roles & Permissions Seeded");
        System.out.println("==================================");
    }

    private void createOrUpdateRole(
            Role roleName,
            Permission... permissions) {

        RoleEntity role = roleRepository
                .findByName(roleName.name())
                .orElse(new RoleEntity());

        role.setName(roleName.name());

        Set<PermissionEntity> permissionSet = new HashSet<>();

        Arrays.stream(permissions).forEach(permission -> {

            PermissionEntity permissionEntity =
                    permissionRepository.findByName(permission)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Permission not found : " + permission));

            permissionSet.add(permissionEntity);
        });

        role.setPermissions(permissionSet);

        roleRepository.save(role);

        System.out.println("Seeded Role : " + roleName.name());
    }
}