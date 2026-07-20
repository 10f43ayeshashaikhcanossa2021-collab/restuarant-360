package com.restaurant.restaurant_backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kitchen")
public class KitchenController {

    @GetMapping("/kot")
    @PreAuthorize("hasAuthority('KOT_VIEW')")
    public String getKOT() {
        return "Kitchen Orders";
    }

    @PutMapping("/kot/{id}")
    @PreAuthorize("hasAuthority('KOT_UPDATE')")
    public String updateKOT(@PathVariable Long id) {
        return "KOT Updated : " + id;
    }
}