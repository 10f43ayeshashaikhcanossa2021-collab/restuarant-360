package com.restaurant.restaurant_backend.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BranchRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;
    @NotBlank
    private String country;

    @NotBlank
    private String state;

    @Email
    @NotBlank
    private String email;

    private Boolean active = true;
}