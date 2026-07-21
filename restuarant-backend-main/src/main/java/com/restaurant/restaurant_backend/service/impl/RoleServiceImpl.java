package com.restaurant.restaurant_backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.restaurant_backend.dto.RoleRequest;
import com.restaurant.restaurant_backend.entity.PermissionEntity;
import com.restaurant.restaurant_backend.entity.RoleEntity;
import com.restaurant.restaurant_backend.entity.User;
import com.restaurant.restaurant_backend.repository.PermissionRepository;
import com.restaurant.restaurant_backend.repository.RoleRepository;
import com.restaurant.restaurant_backend.repository.UserRepository;
import com.restaurant.restaurant_backend.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;

    public RoleServiceImpl(RoleRepository roleRepository,
                           PermissionRepository permissionRepository,
                           UserRepository userRepository) {

        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RoleEntity createRole(RoleRequest request) {

        if (roleRepository.existsByName(request.getName())) {
            throw new RuntimeException("Role already exists");
        }

        RoleEntity role = new RoleEntity();
        role.setName(request.getName());
        role.setDescription(request.getDescription());

        return roleRepository.save(role);
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity getRoleById(Long id) {

        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public RoleEntity updateRole(Long id, RoleRequest request) {

        RoleEntity role = getRoleById(id);

        role.setName(request.getName());
        role.setDescription(request.getDescription());

        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {

        RoleEntity role = getRoleById(id);

        roleRepository.delete(role);
    }

    @Override
    public RoleEntity assignPermission(Long roleId, Long permissionId) {

        RoleEntity role = getRoleById(roleId);

        PermissionEntity permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found"));

        role.getPermissions().add(permission);

        return roleRepository.save(role);
    }

    @Override
    public RoleEntity removePermission(Long roleId, Long permissionId) {

        RoleEntity role = getRoleById(roleId);

        PermissionEntity permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found"));

        role.getPermissions().remove(permission);

        return roleRepository.save(role);
    }

    @Override
    public User assignRoleToUser(Long userId, Long roleId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RoleEntity role = getRoleById(roleId);

        user.getRoles().add(role);

        return userRepository.save(user);
    }

    @Override
    public User removeRoleFromUser(Long userId, Long roleId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RoleEntity role = getRoleById(roleId);

        user.getRoles().remove(role);

        return userRepository.save(user);
    }
}