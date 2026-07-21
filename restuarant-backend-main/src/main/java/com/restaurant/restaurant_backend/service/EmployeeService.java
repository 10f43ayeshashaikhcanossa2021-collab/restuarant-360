package com.restaurant.restaurant_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.restaurant.restaurant_backend.dto.EmployeeRequest;
import com.restaurant.restaurant_backend.dto.EmployeeResponse;
import com.restaurant.restaurant_backend.entity.Branch;
import com.restaurant.restaurant_backend.entity.Employee;
import com.restaurant.restaurant_backend.entity.RoleEntity;
import com.restaurant.restaurant_backend.enums.Role;
import com.restaurant.restaurant_backend.repository.BranchRepository;
import com.restaurant.restaurant_backend.repository.EmployeeRepository;
import com.restaurant.restaurant_backend.repository.RoleRepository;

@Service
public class EmployeeService {
        

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final RoleRepository roleRepository;
private final PasswordEncoder passwordEncoder;
    public EmployeeService(EmployeeRepository employeeRepository,
                           BranchRepository branchRepository,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.roleRepository = roleRepository;this.passwordEncoder = passwordEncoder;
    }

    // Create Employee
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {

    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

    if (!employee.getEmployeeCode().equals(request.getEmployeeCode())
            && employeeRepository.existsByEmployeeCode(request.getEmployeeCode())) {
        throw new RuntimeException("Employee code already exists");
    }

    Branch branch = branchRepository.findById(request.getBranchId())
            .orElseThrow(() -> new RuntimeException("Branch not found"));

   RoleEntity role = roleRepository.findByName(request.getRole().name())
        .orElseThrow(() -> new RuntimeException("Role not found"));

    employee.setEmployeeCode(request.getEmployeeCode());
    employee.setFullName(request.getFullName());
    employee.setEmail(request.getEmail());
    employee.setPhone(request.getPhone());
    employee.setRole(role);
    employee.setBranch(branch);

    if (request.getPin() != null && !request.getPin().isBlank()) {
        employee.setPinHash(passwordEncoder.encode(request.getPin()));
    }

    Employee updated = employeeRepository.save(employee);

    return mapToResponse(updated);
}

    // Get All Employees
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get Employee By Id
    public EmployeeResponse getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return mapToResponse(employee);
    }

    // Update Employee
   public EmployeeResponse createEmployee(EmployeeRequest request) {

    if (employeeRepository.existsByEmail(request.getEmail())) {
        throw new RuntimeException("Employee email already exists");
    }

    if (employeeRepository.existsByEmployeeCode(request.getEmployeeCode())) {
        throw new RuntimeException("Employee code already exists");
    }

    Branch branch = branchRepository.findById(request.getBranchId())
            .orElseThrow(() -> new RuntimeException("Branch not found"));

    RoleEntity role = roleRepository.findByName(request.getRole().name())
        .orElseThrow(() -> new RuntimeException("Role not found"));

    Employee employee = new Employee();

    employee.setEmployeeCode(request.getEmployeeCode());
    employee.setFullName(request.getFullName());
    employee.setEmail(request.getEmail());
    employee.setPhone(request.getPhone());
    employee.setRole(role);
    employee.setBranch(branch);
    employee.setActive(true);

    if (request.getPin() != null && !request.getPin().isBlank()) {
        employee.setPinHash(passwordEncoder.encode(request.getPin()));
    }

    Employee saved = employeeRepository.save(employee);

    return mapToResponse(saved);
}

    // Delete Employee
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeRepository.delete(employee);
    }

    // Activate / Deactivate
    public EmployeeResponse changeStatus(Long id,
                                         boolean active) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setActive(active);

        Employee updated = employeeRepository.save(employee);

        return mapToResponse(updated);
    }

    // Employees By Branch
    public List<EmployeeResponse> getEmployeesByBranch(Long branchId) {

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        return employeeRepository.findByBranch(branch)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private EmployeeResponse mapToResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();

        response.setId(employee.getId());
        response.setFullName(employee.getFullName());
        response.setEmail(employee.getEmail());
        response.setPhone(employee.getPhone());

        if (employee.getRole() != null && employee.getRole().getName() != null) {
            response.setRole(Role.valueOf(employee.getRole().getName()));
        }

        response.setBranchId(employee.getBranch().getId());
        response.setBranchName(employee.getBranch().getName());

        response.setActive(Boolean.TRUE.equals(employee.getActive()));

        return response;
    }
}