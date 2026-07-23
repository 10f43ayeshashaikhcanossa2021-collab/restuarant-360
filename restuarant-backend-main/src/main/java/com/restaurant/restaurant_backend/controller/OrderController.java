package com.restaurant.restaurant_backend.controller;

import com.restaurant.restaurant_backend.dto.OrderRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @GetMapping
    @PreAuthorize("hasAuthority('ORDER_VIEW')")
    public ResponseEntity<?> getOrders() {

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "All Orders");
        response.put("data", new Object[]{});

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ORDER_VIEW')")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Order Found");
        response.put("orderId", id);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ORDER_CREATE')")
    public ResponseEntity<?> createOrder(
            @Valid @RequestBody OrderRequest request) {

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Order Created Successfully");
        response.put("order", request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORDER_CREATE')")
    public ResponseEntity<?> updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequest request) {

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Order Updated Successfully");
        response.put("orderId", id);
        response.put("order", request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasAuthority('ORDER_CANCEL')")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Order Cancelled Successfully");
        response.put("orderId", id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORDER_CANCEL')")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Order Deleted Successfully");
        response.put("orderId", id);

        return ResponseEntity.ok(response);
    }
}