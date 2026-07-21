package com.restaurant.restaurant_backend.dto;

import lombok.Data;

@Data
public class CategoryResponse {

    private Long id;

    private String name;

    private String description;

    private Integer displayOrder;

    private Boolean active;

}