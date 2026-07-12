package com.careerboost.dto;

import java.util.List;

public class AIResumeResponse {

    private int resumeScore;
    private int atsScore;

    private List<String> strengths;
    private List<String> weaknesses;
    private List<String> missingSkills;

    private List<KeywordItem> keywordAnalysis;
    private List<String> atsSuggestions;
    private List<String> projectSuggestions;
    private List<String> interviewTips;
    private List<String> learningRoadmap;

    public AIResumeResponse() {
    }

    public int getResumeScore() {
        return resumeScore;
    }

    public void setResumeScore(int resumeScore) {
        this.resumeScore = resumeScore;
    }

    public int getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(int atsScore) {
        this.atsScore = atsScore;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }


    public List<String> getAtsSuggestions() {
        return atsSuggestions;
    }

    public void setAtsSuggestions(List<String> atsSuggestions) {
        this.atsSuggestions = atsSuggestions;
    }

    public List<String> getProjectSuggestions() {
        return projectSuggestions;
    }

    public void setProjectSuggestions(List<String> projectSuggestions) {
        this.projectSuggestions = projectSuggestions;
    }

    public List<String> getInterviewTips() {
        return interviewTips;
    }

    public void setInterviewTips(List<String> interviewTips) {
        this.interviewTips = interviewTips;
    }

    public List<String> getLearningRoadmap() {
        return learningRoadmap;
    }

    public void setLearningRoadmap(List<String> learningRoadmap) {
        this.learningRoadmap = learningRoadmap;
    }

    public List<KeywordItem> getKeywordAnalysis() {
        return keywordAnalysis;
    }

    public void setKeywordAnalysis(List<KeywordItem> keywordAnalysis) {
        this.keywordAnalysis = keywordAnalysis;
    }
}