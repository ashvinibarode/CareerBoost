package com.careerboost.service;

import com.careerboost.dto.QuestionAnswer;
import com.careerboost.dto.QuestionResponse;
import com.careerboost.dto.QuizResultResponse;
import com.careerboost.dto.QuizSubmitRequest;
import com.careerboost.entity.Question;
import com.careerboost.entity.QuizResult;
import com.careerboost.repository.QuestionRepository;
import com.careerboost.repository.QuizResultRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuizService {

    private final QuestionRepository questionRepository;
    private final QuizResultRepository quizResultRepository;

    public QuizService(QuestionRepository questionRepository,
                       QuizResultRepository quizResultRepository) {

        this.questionRepository = questionRepository;
        this.quizResultRepository = quizResultRepository;
    }

    // Get 10 Random Questions
    public List<QuestionResponse> getQuestions(String technology) {

        List<Question> questions =
                questionRepository.findRandomQuestions(technology, 10);

        return questions.stream()
                .map(q -> new QuestionResponse(
                        q.getId(),
                        q.getQuestion(),
                        q.getOptionA(),
                        q.getOptionB(),
                        q.getOptionC(),
                        q.getOptionD()
                ))
                .toList();
    }

    // Submit Quiz
    public QuizResultResponse submitQuiz(QuizSubmitRequest request) {

        int correct = 0;

        for (QuestionAnswer answer : request.getAnswers()) {

            Question question = questionRepository.findById(answer.getQuestionId())
                    .orElseThrow(() ->
                            new RuntimeException("Question not found"));

            if (question.getCorrectAnswer()
                    .equalsIgnoreCase(answer.getSelectedAnswer())) {
                correct++;
            }
        }

        int total = request.getAnswers().size();
        int wrong = total - correct;

        double percentage = (correct * 100.0) / total;

        String message;

        if (percentage >= 80) {
            message = "Excellent Performance";
        } else if (percentage >= 60) {
            message = "Good Performance";
        } else if (percentage >= 40) {
            message = "Needs Improvement";
        } else {
            message = "Keep Practicing";
        }

        QuizResult result = new QuizResult();

        result.setUserId(request.getUserId());
        result.setTechnology(request.getTechnology());
        result.setDifficulty(request.getDifficulty());
        result.setScore(correct);
        result.setTotalQuestions(total);
        result.setCorrectAnswers(correct);
        result.setWrongAnswers(wrong);
        result.setPercentage(percentage);
        result.setAttemptedAt(LocalDateTime.now());

        quizResultRepository.save(result);

        return new QuizResultResponse(
                correct,
                total,
                correct,
                wrong,
                percentage,
                message
        );
    }
}