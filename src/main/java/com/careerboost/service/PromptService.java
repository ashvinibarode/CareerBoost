package com.careerboost.service;

import org.springframework.stereotype.Service;

@Service
public class PromptService {

    // Resume Analysis Prompt
    public String buildResumePrompt(String resumeText) {

        return """
You are an expert ATS Resume Analyzer and Career Coach.

Analyze the following resume.

Return ONLY valid JSON.

Rules:
1. Return ONLY JSON.
2. Do NOT write explanations.
3. Do NOT use markdown.
4. Do NOT wrap response inside ```json.
5. Never truncate JSON.
6. If the response becomes too large, reduce list sizes instead of cutting JSON.
7. Resume score must be between 0 and 100.

Return exactly this JSON format:

{
  "resumeScore": 0 ,
  "atsScore":0,
  "strengths": [],
  "weaknesses": [],
  "missingSkills": [],
  "keywordAnalysis": [],
  "atsSuggestions": [],
  "projectSuggestions": [],
  "interviewTips": [],
  "learningRoadmap": []
}

Limits:

strengths:
Maximum 5 items.

weaknesses:
Maximum 5 items.

missingSkills:
Maximum 5 items.

keywordAnalysis:
Maximum 8 items.

Each keywordAnalysis item must be:

{
  "keyword":"",
  "status":"",
  "context":""
}

atsSuggestions:
Maximum 5 items.

projectSuggestions:
Maximum 3 items.

interviewTips:
Maximum 3 items.

learningRoadmap:
Maximum 5 items.

Resume:

%s
""".formatted(resumeText);
    }

    // Job Description Analysis Prompt
    public String buildJDPrompt(String resumeText, String jobDescription) {

        return """
You are an ATS Resume Analyzer.

Compare the resume with the given job description.

Return ONLY valid JSON.

{
  "matchScore": 0,
  "matchedSkills": [],
  "missingSkills": [],
  "keywordSuggestions": [],
  "improvementSuggestions": []
}

Rules:
1. Return ONLY JSON.
2. No markdown.
3. No explanation.
4. Maximum 5 items in each list.
5. Never truncate JSON.
6.Never return null. If there are no missing keywords, return an empty array [].
Resume:
--------------------
%s
--------------------

Job Description:
--------------------
%s
--------------------
""".formatted(resumeText, jobDescription);
    }
    public String buildInterviewPrompt(String technology,
                                       String level,
                                       String type) {

        return """
Generate exactly 5 interview questions in valid JSON.

Technology: %s
Level: %s
Type: %s

Return ONLY valid JSON.

{
  "questions": [
    {
      "question": "Question here",
      "answer": "Answer here"
    }
  ]
}
""".formatted(
                technology,
                level,
                type
        );
    }
}