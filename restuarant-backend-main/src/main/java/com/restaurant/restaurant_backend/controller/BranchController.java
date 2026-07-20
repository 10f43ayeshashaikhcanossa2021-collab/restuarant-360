package com.restaurant.restaurant_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurant_backend.dto.BranchRequest;
import com.restaurant.restaurant_backend.dto.BranchResponse;
import com.restaurant.restaurant_backend.service.BranchService;

@RestController
@RequestMapping("/api/branches")
@CrossOrigin(origins = "http://localhost:3000")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    // Create Branch
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('OWNER')")
    @PostMapping
    public ResponseEntity<BranchResponse> createBranch(
            @RequestBody BranchRequest request) {

        return ResponseEntity.ok(branchService.createBranch(request));
    }

    // Get All Branches
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<BranchResponse>> getAllBranches() {

        return ResponseEntity.ok(branchService.getAllBranches());
    }

    // Get Branch By Id
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<BranchResponse> getBranchById(
            @PathVariable Long id) {

        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    // Update Branch
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<BranchResponse> updateBranch(
            @PathVariable Long id,
            @RequestBody BranchRequest request) {

        return ResponseEntity.ok(
                branchService.updateBranch(id, request)
        );
    }

    // Delete Branch
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBranch(
            @PathVariable Long id) {

        branchService.deleteBranch(id);

        return ResponseEntity.ok("Branch deleted successfully");
    }
}