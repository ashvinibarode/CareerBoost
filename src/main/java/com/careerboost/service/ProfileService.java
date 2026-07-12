package com.careerboost.service;

import com.careerboost.dto.ProfileResponse;
import com.careerboost.dto.UpdateProfileRequest;
import com.careerboost.entity.User;
import com.careerboost.entity.UserProfile;
import com.careerboost.repository.UserProfileRepository;
import com.careerboost.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public ProfileService(UserRepository userRepository,
                          UserProfileRepository userProfileRepository) {

        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    // ==========================
    // Get Profile
    // ==========================

    public ProfileResponse getProfile(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        UserProfile profile = userProfileRepository
                .findByUser(user)
                .orElse(new UserProfile());

        ProfileResponse response = new ProfileResponse();

        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        response.setPhone(profile.getPhone());
        response.setEducation(profile.getEducation());
        response.setCollege(profile.getCollege());
        response.setTargetRole(profile.getTargetRole());

        response.setGithubUrl(profile.getGithubUrl());
        response.setLinkedinUrl(profile.getLinkedinUrl());
        response.setPortfolioUrl(profile.getPortfolioUrl());

        response.setSummary(profile.getSummary());

        return response;
    }

    // ==========================
    // Update Profile
    // ==========================

    public ProfileResponse updateProfile(Long userId,
                                         UpdateProfileRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Update User Table
        user.setFullName(request.getFullName());

        userRepository.save(user);

        // Find or Create Profile
        UserProfile profile = userProfileRepository
                .findByUser(user)
                .orElse(new UserProfile());

        profile.setUser(user);

        profile.setPhone(request.getPhone());
        profile.setEducation(request.getEducation());
        profile.setCollege(request.getCollege());
        profile.setTargetRole(request.getTargetRole());

        profile.setGithubUrl(request.getGithubUrl());
        profile.setLinkedinUrl(request.getLinkedinUrl());
        profile.setPortfolioUrl(request.getPortfolioUrl());

        profile.setSummary(request.getSummary());

        userProfileRepository.save(profile);

        return getProfile(userId);
    }

}