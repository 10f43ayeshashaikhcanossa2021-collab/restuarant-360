package com.restaurant.restaurant_backend.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.restaurant.restaurant_backend.entity.Inventory;
import com.restaurant.restaurant_backend.repository.InventoryRepository;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // ===========================
    // Get All Inventory
    // ===========================
    @GetMapping
    @PreAuthorize("hasAuthority('STOCK_VIEW')")
    public List<Inventory> getInventory() {

        return inventoryRepository.findAll();
    }

    // ===========================
    // Get Item By ID
    // ===========================
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('STOCK_VIEW')")
    public Inventory getInventoryItem(@PathVariable Long id) {

        return inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Item Not Found"));
    }

    // ===========================
    // Create Item
    // ===========================
    @PostMapping
    @PreAuthorize("hasAuthority('STOCK_CREATE')")
    public Inventory createInventory(
            @RequestBody Inventory inventory) {

        return inventoryRepository.save(inventory);
    }

    // ===========================
    // Update Stock
    // ===========================
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('STOCK_UPDATE')")
    public Inventory updateInventory(
            @PathVariable Long id,
            @RequestBody Inventory updatedInventory) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Item Not Found"));

        inventory.setItemName(updatedInventory.getItemName());
        inventory.setQuantity(updatedInventory.getQuantity());

        return inventoryRepository.save(inventory);
    }

    // ===========================
    // Delete Item
    // ===========================
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('STOCK_DELETE')")
    public String deleteInventory(@PathVariable Long id) {

        inventoryRepository.deleteById(id);

        return "Inventory Item Deleted";
    }
}