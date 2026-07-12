package com.careerboost.service;

import com.careerboost.dto.ResumeAnalysisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ResumeService {


    @Autowired
    private PdfExtractorService pdfExtractorService;


    // -----------------------------
    // Analyze Resume
    // -----------------------------
    public ResumeAnalysisResponse analyzeResume(Long userId) {

        // Existing AI analysis logic

        return new ResumeAnalysisResponse();
    }
}