package com.careerboost.dto;

import java.util.List;

public class ResumeAnalysisResponse {

    private int score;

    private List<String> missingSections;

    private List<String> recommendations;

    public ResumeAnalysisResponse() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getMissingSections() {
        return missingSections;
    }

    public void setMissingSections(List<String> missingSections) {
        this.missingSections = missingSections;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }
}