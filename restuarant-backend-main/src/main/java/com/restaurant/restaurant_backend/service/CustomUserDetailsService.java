package com.restaurant.restaurant_backend.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    // Add all roles
    user.getRoles().forEach(role -> {

        authorities.add(
                new SimpleGrantedAuthority(
                        "ROLE_" + role.getName()
                )
        );

        // Add permissions of each role
        role.getPermissions().forEach(permission ->

                authorities.add(
                        new SimpleGrantedAuthority(
                                permission.getName().name()
                        )
                )
        );

    });

    return new org.springframework.security.core.userdetails.User(

            user.getEmail(),

            user.getPassword(),

            user.isActive(),

            true,

            true,

            user.getAccountLockedUntil() == null
                    || user.getAccountLockedUntil().isBefore(java.time.LocalDateTime.now()),

            authorities
    );
}
   
}