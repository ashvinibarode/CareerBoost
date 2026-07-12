package com.careerboost.service;

import com.careerboost.dto.AuthResponse;
import com.careerboost.dto.LoginRequest;
import com.careerboost.dto.RegisterRequest;
import com.careerboost.entity.User;
import com.careerboost.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ==========================
    // Register
    // ==========================
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new AuthResponse(false, "Email already exists");
        }

        if (userRepository.findByPhone(request.getPhone()).isPresent()) {
            return new AuthResponse(false, "Phone number already exists");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());
        user.setRole("USER");

        userRepository.save(user);

        return new AuthResponse(
                true,
                "Registration Successful",
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );
    }

    // ==========================
    // Login
    // ==========================
    public AuthResponse login(LoginRequest request) {

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return new AuthResponse(false, "User not found");
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return new AuthResponse(false, "Invalid password");
        }

        return new AuthResponse(
                true,
                "Login Successful",
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );
    }
}