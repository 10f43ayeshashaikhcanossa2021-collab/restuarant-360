package com.restaurant.restaurant_backend.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurant_backend.entity.Outlet;
import com.restaurant.restaurant_backend.repository.OutletRepository;

@RestController
@RequestMapping("/api/outlets")
public class OutletController {

    private final OutletRepository outletRepository;

    public OutletController(OutletRepository outletRepository) {
        this.outletRepository = outletRepository;
    }

    // ==========================
    // Get All Outlets
    // ==========================
    @GetMapping
    @PreAuthorize("hasAuthority('OUTLET_VIEW')")
    public List<Outlet> getOutlets() {
        return outletRepository.findAll();
    }

    // ==========================
    // Get Outlet By ID
    // ==========================
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('OUTLET_VIEW')")
    public Outlet getOutlet(@PathVariable Long id) {

        return outletRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Outlet Not Found"));
    }

    // ==========================
    // Create Outlet
    // ==========================
    @PostMapping
    @PreAuthorize("hasAuthority('OUTLET_CREATE')")
    public Outlet createOutlet(@RequestBody Outlet outlet) {

        return outletRepository.save(outlet);
    }

    // ==========================
    // Update Outlet
    // ==========================
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('OUTLET_UPDATE')")
    public Outlet updateOutlet(
            @PathVariable Long id,
            @RequestBody Outlet updatedOutlet) {

        Outlet outlet = outletRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Outlet Not Found"));

        outlet.setName(updatedOutlet.getName());
        outlet.setAddress(updatedOutlet.getAddress());

        return outletRepository.save(outlet);
    }

    // ==========================
    // Delete Outlet
    // ==========================
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('OUTLET_DELETE')")
    public String deleteOutlet(@PathVariable Long id) {

        outletRepository.deleteById(id);

        return "Outlet Deleted Successfully";
    }

}