package com.restaurant.restaurant_backend.dto;

import com.restaurant.restaurant_backend.enums.Permission;

public class PermissionRequest {

    private Permission name;

    private String description;

    public Permission getName() {
        return name;
    }

    public void setName(Permission name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}