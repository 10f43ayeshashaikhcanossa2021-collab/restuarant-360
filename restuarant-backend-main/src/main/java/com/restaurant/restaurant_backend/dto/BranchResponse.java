package com.restaurant.restaurant_backend.dto;

import lombok.Data;

@Data
public class BranchResponse {

    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;

    private Boolean active;
}