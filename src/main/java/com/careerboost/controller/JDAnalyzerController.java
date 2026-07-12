package com.careerboost.controller;

import com.careerboost.dto.JDAnalysisResponse;
import com.careerboost.service.AIResumeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/jd")
@CrossOrigin(origins = "*")
public class JDAnalyzerController {

    private final AIResumeService aiResumeService;

    public JDAnalyzerController(AIResumeService aiResumeService) {
        this.aiResumeService = aiResumeService;
    }

    @PostMapping(value = "/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JDAnalysisResponse> analyzeJobDescription(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobDescription") String jobDescription)
            throws Exception {
// Validation
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        if (jobDescription == null || jobDescription.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        System.out.println(file.getOriginalFilename());
        System.out.println(jobDescription);

        return ResponseEntity.ok(
                aiResumeService.analyzeJobDescription(file, jobDescription)
        );
    }
}