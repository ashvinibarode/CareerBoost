package com.careerboost.service;

import com.careerboost.dto.DashboardResponse;

import com.careerboost.entity.User;

import com.careerboost.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class DashboardService {

    private final UserRepository userRepository;


    public DashboardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public DashboardResponse getDashboard(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String resumeFileName = null;
        LocalDateTime uploadedAt = null;


        return new DashboardResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );

    }
}