package com.careerboost.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class JDAnalysisResponse {

    private int matchScore;

    private List<String> matchedSkills;
    private List<String> missingSkills;

    private List<String> missingKeywords;

    private List<String> keywordSuggestions;

    private List<String> improvementSuggestions;



    public JDAnalysisResponse() {
    }

    public int getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }



    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public List<String> getMissingKeywords() {
        return missingKeywords;
    }

    public void setMissingKeywords(List<String> missingKeywords) {
        this.missingKeywords = missingKeywords;
    }


    public List<String> getImprovementSuggestions() {
        return improvementSuggestions;
    }

    public void setImprovementSuggestions(List<String> improvementSuggestions) {
        this.improvementSuggestions = improvementSuggestions;
    }

    public List<String> getKeywordSuggestions() {
        return keywordSuggestions;
    }

    public void setKeywordSuggestions(List<String> keywordSuggestions) {
        this.keywordSuggestions = keywordSuggestions;
    }
}