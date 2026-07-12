package com.careerboost.controller;

import com.careerboost.dto.InterviewRequest;
import com.careerboost.dto.InterviewResponse;
import com.careerboost.service.AIResumeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview")
@CrossOrigin(origins = "*")
public class InterviewController {

    private final AIResumeService aiResumeService;

    public InterviewController(AIResumeService aiResumeService) {
        this.aiResumeService = aiResumeService;
    }

    @PostMapping("/generate")
    public InterviewResponse generateQuestions(
            @RequestBody InterviewRequest request)
            throws Exception {

        return aiResumeService.generateInterviewQuestions(request);
    }
}