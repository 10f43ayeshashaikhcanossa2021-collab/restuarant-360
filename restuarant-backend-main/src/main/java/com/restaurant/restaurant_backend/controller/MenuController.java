package com.restaurant.restaurant_backend.controller;

import com.restaurant.restaurant_backend.dto.MenuRequest;
import com.restaurant.restaurant_backend.dto.MenuResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @GetMapping
    @PreAuthorize("hasAuthority('MENU_VIEW')")
    public ResponseEntity<List<MenuResponse>> getMenu() {

        List<MenuResponse> menu = Arrays.asList(
                new MenuResponse(1L, "Chicken Biryani", 250.0, "Main Course", true),
                new MenuResponse(2L, "Veg Fried Rice", 180.0, "Main Course", true),
                new MenuResponse(3L, "Cold Coffee", 120.0, "Beverages", true)
        );

        return ResponseEntity.ok(menu);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MENU_CREATE')")
    public ResponseEntity<MenuResponse> createMenu(
            @RequestBody MenuRequest request) {

        MenuResponse response = new MenuResponse(
                1L,
                request.getName(),
                request.getPrice(),
                request.getCategory(),
                request.isAvailable()
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MENU_UPDATE')")
    public ResponseEntity<MenuResponse> updateMenu(
            @PathVariable Long id,
            @RequestBody MenuRequest request) {

        MenuResponse response = new MenuResponse(
                id,
                request.getName(),
                request.getPrice(),
                request.getCategory(),
                request.isAvailable()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MENU_DELETE')")
    public ResponseEntity<String> deleteMenu(@PathVariable Long id) {

        return ResponseEntity.ok("Menu item deleted successfully. ID = " + id);
    }
}