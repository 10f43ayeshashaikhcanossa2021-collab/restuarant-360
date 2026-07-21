package com.restaurant.restaurant_backend.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MenuItemRequest {

    @NotBlank
    private String name;

    private String description;

    @Positive
    private BigDecimal price;

    private String imageUrl;

    private Boolean veg;

    private Boolean available;

    private Integer preparationTime;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long branchId;

}