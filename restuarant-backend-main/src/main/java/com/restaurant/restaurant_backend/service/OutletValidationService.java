package com.restaurant.restaurant_backend.service;

import org.springframework.stereotype.Service;

@Service
public class OutletValidationService {

    private final CurrentUserService currentUserService;

    public OutletValidationService(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    public void validateOutlet(Long outletId) {

        Long userOutletId =
                currentUserService
                        .getCurrentUser()
                        .getOutlet()
                        .getId();

        if (!userOutletId.equals(outletId)) {

            throw new RuntimeException(
                    "Access Denied for this outlet."
            );
        }
    }
}