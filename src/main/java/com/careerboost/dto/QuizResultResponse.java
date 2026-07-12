package com.careerboost.dto;

public class QuizResultResponse {

    private Integer score;
    private Integer totalQuestions;
    private Integer correctAnswers;
    private Integer wrongAnswers;
    private Double percentage;
    private String message;

    public QuizResultResponse() {
    }

    public QuizResultResponse(Integer score,
                              Integer totalQuestions,
                              Integer correctAnswers,
                              Integer wrongAnswers,
                              Double percentage,
                              String message) {

        this.score = score;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
        this.percentage = percentage;
        this.message = message;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public Integer getWrongAnswers() {
        return wrongAnswers;
    }

    public Double getPercentage() {
        return percentage;
    }

    public String getMessage() {
        return message;
    }
}