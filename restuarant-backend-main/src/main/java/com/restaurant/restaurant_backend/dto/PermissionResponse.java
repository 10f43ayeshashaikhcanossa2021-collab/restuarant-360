package com.restaurant.restaurant_backend.dto;

public class PermissionResponse {

    private Long id;

    private String name;

    private String description;

    public PermissionResponse() {
    }

    public PermissionResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // getters & setters
}