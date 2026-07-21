package com.restaurant.restaurant_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {

    @NotBlank
    private String name;

    private String description;

    private Integer displayOrder;

    private Boolean active=true;

}