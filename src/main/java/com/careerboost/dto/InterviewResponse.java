package com.careerboost.dto;

import java.util.List;

public class InterviewResponse {

    private List<InterviewQuestionResponse> questions;

    public InterviewResponse() {
    }

    public InterviewResponse(List<InterviewQuestionResponse> questions) {
        this.questions = questions;
    }

    public List<InterviewQuestionResponse> getQuestions() {
        return questions;
    }

    public void setQuestions(List<InterviewQuestionResponse> questions) {
        this.questions = questions;
    }
}