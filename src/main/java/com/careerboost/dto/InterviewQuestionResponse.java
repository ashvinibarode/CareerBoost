package com.careerboost.dto;

public class InterviewQuestionResponse {

    private String question;
    private String answer;

    public InterviewQuestionResponse() {
    }

    public InterviewQuestionResponse(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}