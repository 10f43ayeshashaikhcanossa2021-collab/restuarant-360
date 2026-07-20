package com.restaurant.restaurant_backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @GetMapping("/sales")
    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public String salesReport() {
        return "Sales Report";
    }

    @GetMapping("/inventory")
    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public String inventoryReport() {
        return "Inventory Report";
    }

    @GetMapping("/employees")
    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public String employeeReport() {
        return "Employee Report";
    }
}