package com.restaurant.restaurant_backend.controller;
import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurant_backend.dto.ChangePasswordRequest;
import com.restaurant.restaurant_backend.dto.ForgotPasswordRequest;
import com.restaurant.restaurant_backend.dto.GoogleLoginRequest;
import com.restaurant.restaurant_backend.dto.GoogleLoginResponse;
import com.restaurant.restaurant_backend.dto.LoginRequest;
import com.restaurant.restaurant_backend.dto.LoginResponse;
import com.restaurant.restaurant_backend.dto.LogoutRequest;
import com.restaurant.restaurant_backend.dto.PinLoginRequest;
import com.restaurant.restaurant_backend.dto.RefreshRequest;
import com.restaurant.restaurant_backend.dto.RefreshResponse;
import com.restaurant.restaurant_backend.dto.RegisterRequest;
import com.restaurant.restaurant_backend.dto.ResetPasswordRequest;
import com.restaurant.restaurant_backend.dto.UserResponse;
import com.restaurant.restaurant_backend.service.AuthenticationService;
import com.restaurant.restaurant_backend.service.PasswordResetService;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final PasswordResetService passwordResetService;
   

   public AuthController(
        AuthenticationService authenticationService,
        PasswordResetService passwordResetService) {

    this.authenticationService = authenticationService;
    this.passwordResetService = passwordResetService;
    
}
    @PostMapping("/login")
    public RefreshResponse login(@RequestBody LoginRequest request) {
        return authenticationService.authenticateWithRefresh(request);
    }
    @PostMapping("/forgot-password")
public ResponseEntity<String> forgotPassword(
        @RequestBody ForgotPasswordRequest request) {

    String token =
            passwordResetService.forgotPassword(
                    request.getEmail()
            );

    return ResponseEntity.ok(token);
}
@PostMapping("/pin-login")
public RefreshResponse pinLogin(
        @RequestBody PinLoginRequest request) {

    return authenticationService.loginWithPin(request);
}

    @PostMapping("/refresh")
public ResponseEntity<RefreshResponse> refreshToken(
        @RequestBody RefreshRequest request) {

    return ResponseEntity.ok(
            authenticationService.refreshToken(request)
    );
}
@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

    try {
        String token = authenticationService.register(request);
        return ResponseEntity.ok(new LoginResponse(token));

    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }
}
@PostMapping("/reset-password")
public ResponseEntity<String> resetPassword(
        @RequestBody ResetPasswordRequest request) {

    passwordResetService.resetPassword(
            request.getToken(),
            request.getNewPassword()
    );

    return ResponseEntity.ok("Password changed successfully");
}
    @PostMapping("/logout")
public ResponseEntity<String> logout(
        @RequestBody LogoutRequest request) {

    authenticationService.logout(request);

    return ResponseEntity.ok("Logout Successful");
}
@GetMapping("/me")
public ResponseEntity<UserResponse> getCurrentUser(
        Authentication authentication) {

    return ResponseEntity.ok(
            authenticationService.getCurrentUser(authentication.getName())
    );
}
@PostMapping("/change-password")
public ResponseEntity<String> changePassword(
        Principal principal,
        @RequestBody ChangePasswordRequest request) {

    authenticationService.changePassword(
            principal.getName(),
            request.getOldPassword(),
            request.getNewPassword()
    );

    return ResponseEntity.ok("Password changed successfully");
}
@PostMapping("/google")
public ResponseEntity<GoogleLoginResponse> googleLogin(
        @RequestBody GoogleLoginRequest request)
        throws Exception {

    return ResponseEntity.ok(
            authenticationService.googleLogin(request)
    );
}

}