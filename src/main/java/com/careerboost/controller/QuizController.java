package com.careerboost.controller;

import com.careerboost.dto.QuestionResponse;
import com.careerboost.dto.QuizResultResponse;
import com.careerboost.dto.QuizSubmitRequest;
import com.careerboost.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "*")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // Get Random Questions
    @GetMapping("/{technology}")
    public List<QuestionResponse> getQuestions(
            @PathVariable String technology) {

        return quizService.getQuestions(technology);
    }

    // Submit Quiz
    @PostMapping("/submit")
    public QuizResultResponse submitQuiz(
            @RequestBody QuizSubmitRequest request) {

        return quizService.submitQuiz(request);
    }
}