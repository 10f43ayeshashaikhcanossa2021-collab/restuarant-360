package com.restaurant.restaurant_backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @PostMapping("/process")
    @PreAuthorize("hasAuthority('PAYMENT_PROCESS')")
    public String processPayment() {
        return "Payment Successful";
    }

    @GetMapping
    @PreAuthorize("hasAuthority('PAYMENT_VIEW')")
    public String getPayments() {
        return "Payment History";
    }
}