package com.restaurant.restaurant_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuResponse {

    private Long id;
    private String name;
    private Double price;
    private String category;
    private boolean available;
}