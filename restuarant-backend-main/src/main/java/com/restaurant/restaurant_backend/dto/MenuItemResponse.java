package com.restaurant.restaurant_backend.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MenuItemResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String imageUrl;

    private Boolean veg;

    private Boolean available;

    private Integer preparationTime;

    private String categoryName;

    private String branchName;

}