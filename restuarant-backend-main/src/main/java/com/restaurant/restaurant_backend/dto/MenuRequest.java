package com.restaurant.restaurant_backend.dto;

import lombok.Data;

@Data
public class MenuRequest {

    private String name;
    private Double price;
    private String category;
    private boolean available;
}