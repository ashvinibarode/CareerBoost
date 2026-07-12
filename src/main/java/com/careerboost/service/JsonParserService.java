package com.careerboost.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonParserService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T parse(String response, Class<T> clazz) throws Exception {

        if (response == null || response.isBlank()) {
            throw new RuntimeException("Empty AI response");
        }

        String cleanResponse = response.trim();

        // Remove ```json
        if (cleanResponse.startsWith("```json")) {
            cleanResponse = cleanResponse.replaceFirst("```json", "").trim();
        }

        // Remove ```
        if (cleanResponse.endsWith("```")) {
            cleanResponse = cleanResponse.substring(0,
                    cleanResponse.length() - 3).trim();
        }

        return objectMapper.readValue(cleanResponse, clazz);
    }
}