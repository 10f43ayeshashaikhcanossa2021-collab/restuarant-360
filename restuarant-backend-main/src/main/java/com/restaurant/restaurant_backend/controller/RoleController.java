package com.restaurant.restaurant_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurant_backend.dto.RoleRequest;
import com.restaurant.restaurant_backend.entity.RoleEntity;
import com.restaurant.restaurant_backend.entity.User;
import com.restaurant.restaurant_backend.service.RoleService;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Create Role
    @PostMapping
    public ResponseEntity<RoleEntity> createRole(
            @RequestBody RoleRequest request) {

        RoleEntity role = roleService.createRole(request);

        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    // Get All Roles
    @GetMapping
    public ResponseEntity<List<RoleEntity>> getAllRoles() {

        return ResponseEntity.ok(roleService.getAllRoles());
    }

    // Get Role By ID
    @GetMapping("/{id}")
    public ResponseEntity<RoleEntity> getRoleById(
            @PathVariable Long id) {

        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    // Update Role
    @PutMapping("/{id}")
    public ResponseEntity<RoleEntity> updateRole(
            @PathVariable Long id,
            @RequestBody RoleRequest request) {

        return ResponseEntity.ok(
                roleService.updateRole(id, request));
    }

    // Delete Role
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(
            @PathVariable Long id) {

        roleService.deleteRole(id);

        return ResponseEntity.ok("Role deleted successfully.");
    }

    // Assign Permission to Role
    @PostMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<RoleEntity> assignPermission(
            @PathVariable Long roleId,
            @PathVariable Long permissionId) {

        return ResponseEntity.ok(
                roleService.assignPermission(roleId, permissionId));
    }

    // Remove Permission from Role
    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<RoleEntity> removePermission(
            @PathVariable Long roleId,
            @PathVariable Long permissionId) {

        return ResponseEntity.ok(
                roleService.removePermission(roleId, permissionId));
    }

    // Assign Role to User
    @PostMapping("/users/{userId}/{roleId}")
    public ResponseEntity<User> assignRoleToUser(
            @PathVariable Long userId,
            @PathVariable Long roleId) {

        return ResponseEntity.ok(
                roleService.assignRoleToUser(userId, roleId));
    }

    // Remove Role from User
    @DeleteMapping("/users/{userId}/{roleId}")
    public ResponseEntity<User> removeRoleFromUser(
            @PathVariable Long userId,
            @PathVariable Long roleId) {

        return ResponseEntity.ok(
                roleService.removeRoleFromUser(userId, roleId));
    }

}