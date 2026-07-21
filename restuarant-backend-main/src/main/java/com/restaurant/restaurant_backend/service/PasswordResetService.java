package com.restaurant.restaurant_backend.service;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.restaurant.restaurant_backend.entity.PasswordResetToken;
import com.restaurant.restaurant_backend.entity.User;
import com.restaurant.restaurant_backend.repository.PasswordResetTokenRepository;
import com.restaurant.restaurant_backend.repository.UserRepository;

@Service
public class PasswordResetService {
   
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswordResetService(
            UserRepository userRepository,
            PasswordResetTokenRepository passwordResetTokenRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public String forgotPassword(String email) {

    // Find user by email
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    // Generate random token
    String token = UUID.randomUUID().toString();

    // Create PasswordResetToken object
    PasswordResetToken resetToken = new PasswordResetToken();

    resetToken.setToken(token);

    resetToken.setUser(user);

    resetToken.setExpiryDate(
            LocalDateTime.now().plusMinutes(30)
    );

    resetToken.setUsed(false);

    // Save token into database
    passwordResetTokenRepository.save(resetToken);

    return token;
}
public void resetPassword(String token, String newPassword) {

    PasswordResetToken resetToken =
            passwordResetTokenRepository.findByToken(token)
                    .orElseThrow(() ->
                            new RuntimeException("Invalid token"));

    if (resetToken.getUsed()) {
        throw new RuntimeException("Token already used");
    }

    if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
        throw new RuntimeException("Token expired");
    }

    User user = resetToken.getUser();

    user.setPassword(passwordEncoder.encode(newPassword));

    userRepository.save(user);

    resetToken.setUsed(true);

    passwordResetTokenRepository.save(resetToken);
}

}