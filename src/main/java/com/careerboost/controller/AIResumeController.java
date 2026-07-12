package com.careerboost.controller;

import com.careerboost.dto.AIResumeResponse;
import com.careerboost.service.AIResumeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ai")
@CrossOrigin("*")
public class AIResumeController {

    private final AIResumeService aiResumeService;

    public AIResumeController(AIResumeService aiResumeService) {
        this.aiResumeService = aiResumeService;
    }

    // Analyze Resume Text
    @PostMapping("/resume")
    public AIResumeResponse analyzeResume(@RequestBody String resumeText)
            throws Exception { return aiResumeService.analyzeResume(resumeText); }

    // Analyze Resume PDF
    @PostMapping("/resume/pdf")
    public AIResumeResponse analyzePdfResume(@RequestParam("file") MultipartFile file) throws Exception {

        return aiResumeService.analyzePdfResume(file);

    }
}