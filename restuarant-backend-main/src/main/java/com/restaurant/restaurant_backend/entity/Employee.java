package com.restaurant.restaurant_backend.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Data
@Table(name="employees")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(unique = true)
    private String employeeCode;



    private String fullName;



    private String email;



    private String phone;



    private String pinHash;



    private Boolean pinEnabled=true;



    private Integer failedPinAttempts=0;



    private LocalDateTime lockedUntil;



    @ManyToOne
    @JoinColumn(name="branch_id")
    private Branch branch;



    @ManyToOne
    @JoinColumn(name="role_id")
    private RoleEntity role;



    private Boolean active=true;


}