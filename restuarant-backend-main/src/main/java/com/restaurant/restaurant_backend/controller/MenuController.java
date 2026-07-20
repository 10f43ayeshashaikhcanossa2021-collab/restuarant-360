package com.restaurant.restaurant_backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @GetMapping
    @PreAuthorize("hasAuthority('MENU_VIEW')")
    public String getMenu() {
        return "Menu List";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MENU_CREATE')")
    public String createMenu() {
        return "Menu Created";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MENU_UPDATE')")
    public String updateMenu(@PathVariable Long id) {
        return "Menu Updated : " + id;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MENU_DELETE')")
    public String deleteMenu(@PathVariable Long id) {
        return "Menu Deleted : " + id;
    }
}