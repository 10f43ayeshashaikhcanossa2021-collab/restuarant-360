package com.restaurant.restaurant_backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @GetMapping
    @PreAuthorize("hasAuthority('ORDER_VIEW')")
    public String getOrders() {
        return "All Orders";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ORDER_CREATE')")
    public String createOrder() {
        return "Order Created";
    }

    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasAuthority('ORDER_CANCEL')")
    public String cancelOrder(@PathVariable Long id) {
        return "Order Cancelled : " + id;
    }
}