package com.restaurant.restaurant_backend.dto;

import com.restaurant.restaurant_backend.enums.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String employeeCode;

    @NotBlank
    private String email;

    private String phone;

    @NotBlank
    private String pin;

    @NotNull
    private Long branchId;

    @NotNull
    private Role role;
}