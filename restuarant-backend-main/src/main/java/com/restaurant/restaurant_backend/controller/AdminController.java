package com.restaurant.restaurant_backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/api/admin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String admin() {
        return "Welcome Super Admin";
    }
    @GetMapping("/api/owner")
@PreAuthorize("hasAnyRole('OWNER','SUPER_ADMIN')")
public String owner() {

    return "Welcome Owner";

}
@GetMapping("/api/cashier")
@PreAuthorize("hasRole('CASHIER')")
public String cashier() {

    return "Cashier Dashboard";


}
@GetMapping("/api/chef")
@PreAuthorize("hasRole('CHEF')")
public String chef() {

    return "Kitchen Dashboard";

}
@GetMapping("/api/create-user")
@PreAuthorize("hasAuthority('USER_CREATE')")
public String createUserPermission() {

    return "Permission USER_CREATE Granted";

}


}