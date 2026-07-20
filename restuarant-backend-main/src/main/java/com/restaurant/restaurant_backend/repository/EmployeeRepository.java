package com.restaurant.restaurant_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.restaurant_backend.entity.Branch;
import com.restaurant.restaurant_backend.entity.Employee;


@Repository
public interface EmployeeRepository 
        extends JpaRepository<Employee, Long> {


    Optional<Employee> findByEmail(String email);


    Optional<Employee> findByEmployeeCode(String employeeCode);


    boolean existsByEmail(String email);


    List<Employee> findByBranch(Branch branch);

}