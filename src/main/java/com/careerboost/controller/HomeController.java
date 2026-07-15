package com.careerboost.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("application", "CareerBoost");
        response.put("status", "Running");
        response.put("version", "1.0");
        response.put("backend", "Spring Boot");
        response.put("message", "CareerBoost Backend is Running Successfully 🚀");

        return response;
    }
}