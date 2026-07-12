package com.careerboost.service;

import com.careerboost.dto.AIResumeResponse;
import com.careerboost.dto.JDAnalysisResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.careerboost.dto.InterviewRequest;
import com.careerboost.dto.InterviewResponse;

@Service
public class AIResumeService {

    private final ChatClient chatClient;
    private final PromptService promptService;
    private final PdfExtractorService pdfExtractorService;
    private final JsonParserService jsonParserService;

    public AIResumeService(
            ChatClient.Builder chatClientBuilder,
            PromptService promptService,
            PdfExtractorService pdfExtractorService,
            JsonParserService jsonParserService) {

        this.chatClient = chatClientBuilder.build();
        this.promptService = promptService;
        this.pdfExtractorService = pdfExtractorService;
        this.jsonParserService = jsonParserService;
    }

    // ===========================
    // Resume Text Analysis
    // ===========================
    public AIResumeResponse analyzeResume(String resumeText) throws Exception {

        String prompt = promptService.buildResumePrompt(resumeText);

        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        System.out.println("========== GEMINI RESUME RESPONSE ==========");
        System.out.println(response);
        System.out.println("============================================");

        // Remove markdown if Gemini returns it
        response = response.replace("```json", "")
                .replace("```", "")
                .trim();

        // Basic JSON validation
        if (!response.startsWith("{") || !response.endsWith("}")) {
            throw new RuntimeException("""
                Gemini returned incomplete JSON.

           Response:
           %s 
           """
                    .formatted(response));
        }

        try {
            return jsonParserService.parse(response, AIResumeResponse.class);
        } catch (Exception e) {

            System.out.println("========= JSON PARSE FAILED =========");
            System.out.println(response);
            System.out.println("=====================================");

            throw new RuntimeException("Unable to parse Gemini JSON.", e);
        }
    }


    // ===========================
    // Resume PDF Analysis
    // ===========================

    public AIResumeResponse analyzePdfResume(MultipartFile file) throws Exception {

        String resumeText = pdfExtractorService.extractText(file);

        return analyzeResume(resumeText);
    }

    // ===========================
    // Resume + Job Description
    // ===========================

    public JDAnalysisResponse analyzeJobDescription(
            MultipartFile file,
            String jobDescription) throws Exception {

        String resumeText = pdfExtractorService.extractText(file);

        String prompt = promptService.buildJDPrompt(
                resumeText,
                jobDescription
        );

        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        System.out.println("========== GEMINI JD RESPONSE ==========");
        System.out.println(response);
        System.out.println("========================================");

        response = cleanJson(response);

        return jsonParserService.parse(response, JDAnalysisResponse.class);
    }

    // ===========================
    // Remove Markdown
    // ===========================

    private String cleanJson(String response) {

        if (response == null) {
            return "";
        }

        response = response.trim();

        if (response.startsWith("```json")) {
            response = response.substring(7);
        }

        if (response.startsWith("```")) {
            response = response.substring(3);
        }

        if (response.endsWith("```")) {
            response = response.substring(0, response.length() - 3);
        }

        return response.trim();

    }
    public InterviewResponse generateInterviewQuestions(
            InterviewRequest request) throws Exception {

        String prompt = promptService.buildInterviewPrompt(
                request.getTechnology(),
                request.getLevel(),
                request.getType()
        );

        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        response = cleanJson(response);

        return jsonParserService.parse(
                response,
                InterviewResponse.class
        );
    }
}