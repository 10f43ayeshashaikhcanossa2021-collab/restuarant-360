package com.restaurant.restaurant_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurant_backend.dto.MenuItemRequest;
import com.restaurant.restaurant_backend.dto.MenuItemResponse;
import com.restaurant.restaurant_backend.service.MenuItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    // Create Menu Item
    @PostMapping
    public ResponseEntity<MenuItemResponse> create(
            @Valid @RequestBody MenuItemRequest request) {

        return ResponseEntity.ok(menuItemService.create(request));
    }

    // Get All Menu Items
    @GetMapping
    public ResponseEntity<List<MenuItemResponse>> getAll() {

        return ResponseEntity.ok(menuItemService.getAll());
    }

    // Get Menu Item By Id
    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(menuItemService.getById(id));
    }

    // Update Menu Item
    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody MenuItemRequest request) {

        return ResponseEntity.ok(menuItemService.update(id, request));
    }

    // Delete Menu Item
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        menuItemService.delete(id);

        return ResponseEntity.ok("Menu Item Deleted Successfully");
    }

    // Get Menu Items By Category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<MenuItemResponse>> getByCategory(
            @PathVariable Long categoryId) {

        return ResponseEntity.ok(menuItemService.getByCategory(categoryId));
    }

    // Get Menu Items By Branch
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<MenuItemResponse>> getByBranch(
            @PathVariable Long branchId) {

        return ResponseEntity.ok(menuItemService.getByBranch(branchId));
    }

    // Change Availability
    @PatchMapping("/{id}/availability")
    public ResponseEntity<MenuItemResponse> changeAvailability(
            @PathVariable Long id,
            @RequestParam boolean available) {

        return ResponseEntity.ok(
                menuItemService.changeAvailability(id, available));
    }
}