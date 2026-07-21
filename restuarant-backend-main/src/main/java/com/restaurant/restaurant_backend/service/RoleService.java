package com.restaurant.restaurant_backend.service;

import java.util.List;

import com.restaurant.restaurant_backend.dto.RoleRequest;
import com.restaurant.restaurant_backend.entity.RoleEntity;
import com.restaurant.restaurant_backend.entity.User;

public interface RoleService {

    RoleEntity createRole(RoleRequest request);

    List<RoleEntity> getAllRoles();

    RoleEntity getRoleById(Long id);

    RoleEntity updateRole(Long id, RoleRequest request);

    void deleteRole(Long id);

    RoleEntity assignPermission(Long roleId, Long permissionId);

    RoleEntity removePermission(Long roleId, Long permissionId);

    User assignRoleToUser(Long userId, Long roleId);

    User removeRoleFromUser(Long userId, Long roleId);
}