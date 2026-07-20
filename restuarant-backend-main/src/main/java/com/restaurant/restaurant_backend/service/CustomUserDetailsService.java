package com.restaurant.restaurant_backend.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.restaurant.restaurant_backend.entity.RoleEntity;
import com.restaurant.restaurant_backend.entity.User;
import com.restaurant.restaurant_backend.repository.RoleRepository;
import com.restaurant.restaurant_backend.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository ,  RoleRepository roleRepository) {
        this.userRepository = userRepository;this.roleRepository = roleRepository;
    }

   @Override
public UserDetails loadUserByUsername(String email)
        throws UsernameNotFoundException {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));

    RoleEntity role = roleRepository
            .findByName(user.getRole().getName())
            .orElseThrow(() ->
                    new RuntimeException("Role not found"));

    List<SimpleGrantedAuthority> authorities =
            new ArrayList<>();

    // Add Role
    authorities.add(
            new SimpleGrantedAuthority(
                    "ROLE_" + role.getName()
            )
    );

    // Add Permissions
    role.getPermissions().forEach(permission ->

            authorities.add(
                    new SimpleGrantedAuthority(
                            permission.getName().name()
                    )
            )
    );

    return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            authorities
    );
}
   
}