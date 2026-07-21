package com.restaurant.restaurant_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.restaurant.restaurant_backend.dto.MenuItemRequest;
import com.restaurant.restaurant_backend.dto.MenuItemResponse;
import com.restaurant.restaurant_backend.entity.Branch;
import com.restaurant.restaurant_backend.entity.Category;
import com.restaurant.restaurant_backend.entity.MenuItem;
import com.restaurant.restaurant_backend.repository.BranchRepository;
import com.restaurant.restaurant_backend.repository.CategoryRepository;
import com.restaurant.restaurant_backend.repository.MenuItemRepository;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;
    private final BranchRepository branchRepository;

    public MenuItemService(
            MenuItemRepository menuItemRepository,
            CategoryRepository categoryRepository,
            BranchRepository branchRepository) {

        this.menuItemRepository = menuItemRepository;
        this.categoryRepository = categoryRepository;
        this.branchRepository = branchRepository;
    }

    // Create Menu Item
    public MenuItemResponse create(MenuItemRequest request) {

        if (menuItemRepository.existsByName(request.getName())) {
            throw new RuntimeException("Menu item already exists");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        MenuItem item = new MenuItem();

        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        item.setImageUrl(request.getImageUrl());
        item.setVeg(request.getVeg());
        item.setAvailable(request.getAvailable());
        item.setPreparationTime(request.getPreparationTime());
        item.setCategory(category);
        item.setBranch(branch);

        return map(menuItemRepository.save(item));
    }

    // Get All
    public List<MenuItemResponse> getAll() {

        return menuItemRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    // Get By Id
    public MenuItemResponse getById(Long id) {

        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu Item not found"));

        return map(item);
    }

    // Update
    public MenuItemResponse update(Long id, MenuItemRequest request) {

        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu Item not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        item.setImageUrl(request.getImageUrl());
        item.setVeg(request.getVeg());
        item.setAvailable(request.getAvailable());
        item.setPreparationTime(request.getPreparationTime());
        item.setCategory(category);
        item.setBranch(branch);

        return map(menuItemRepository.save(item));
    }

    // Delete
    public void delete(Long id) {

        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu Item not found"));

        menuItemRepository.delete(item);
    }

    // By Category
    public List<MenuItemResponse> getByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return menuItemRepository.findByCategory(category)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    // By Branch
    public List<MenuItemResponse> getByBranch(Long branchId) {

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        return menuItemRepository.findByBranch(branch)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    // Change Availability
    public MenuItemResponse changeAvailability(Long id, boolean available) {

        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu Item not found"));

        item.setAvailable(available);

        return map(menuItemRepository.save(item));
    }

    private MenuItemResponse map(MenuItem item) {

        MenuItemResponse response = new MenuItemResponse();

        response.setId(item.getId());
        response.setName(item.getName());
        response.setDescription(item.getDescription());
        response.setPrice(item.getPrice());
        response.setImageUrl(item.getImageUrl());
        response.setVeg(item.getVeg());
        response.setAvailable(item.getAvailable());
        response.setPreparationTime(item.getPreparationTime());
        response.setCategoryName(item.getCategory().getName());
        response.setBranchName(item.getBranch().getName());

        return response;
    }
}