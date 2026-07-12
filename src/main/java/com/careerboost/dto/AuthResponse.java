package com.careerboost.dto;

public class AuthResponse {

    private boolean success;
    private String message;

    private Long userId;
    private String fullName;
    private String email;
    private String role;

    public AuthResponse() {
    }

    // Failure Response
    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Success Response
    public AuthResponse(boolean success,
                        String message,
                        Long userId,
                        String fullName,
                        String email,
                        String role) {

        this.success = success;
        this.message = message;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}