package com.restaurant.restaurant_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.restaurant.restaurant_backend.dto.BranchRequest;
import com.restaurant.restaurant_backend.dto.BranchResponse;
import com.restaurant.restaurant_backend.entity.Branch;
import com.restaurant.restaurant_backend.repository.BranchRepository;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    // Create Branch
    public BranchResponse createBranch(BranchRequest request) {

        if (branchRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Branch email already exists");
        }

        if (branchRepository.existsByName(request.getName())) {
            throw new RuntimeException("Branch name already exists");
        }

        Branch branch = new Branch();

        branch.setName(request.getName());
        branch.setAddress(request.getAddress());
        
        branch.setPhone(request.getPhone());
        branch.setEmail(request.getEmail());

        Branch saved = branchRepository.save(branch);

        return mapToResponse(saved);
    }

    // Get All Branches
    public List<BranchResponse> getAllBranches() {
        return branchRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get Branch By ID
    public BranchResponse getBranchById(Long id) {

        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        return mapToResponse(branch);
    }

    // Update Branch
    public BranchResponse updateBranch(Long id, BranchRequest request) {

        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        branch.setName(request.getName());
        branch.setAddress(request.getAddress());
        
        branch.setPhone(request.getPhone());
        branch.setEmail(request.getEmail());

        Branch updated = branchRepository.save(branch);

        return mapToResponse(updated);
    }

    // Delete Branch
    public void deleteBranch(Long id) {

        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        branchRepository.delete(branch);
    }

    // Entity → DTO
    private BranchResponse mapToResponse(Branch branch) {

        BranchResponse response = new BranchResponse();

        response.setId(branch.getId());
        response.setName(branch.getName());
        response.setAddress(branch.getAddress());
        
        response.setPhone(branch.getPhone());
        response.setEmail(branch.getEmail());
        response.setActive(branch.getActive());

        return response;
    }
}