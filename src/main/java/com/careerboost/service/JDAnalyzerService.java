package com.careerboost.service;

import com.careerboost.dto.JDAnalysisResponse;
import com.careerboost.entity.Skill;
import com.careerboost.entity.User;
import com.careerboost.repository.SkillRepository;
import com.careerboost.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JDAnalyzerService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public JDAnalyzerService(SkillRepository skillRepository,
                             UserRepository userRepository) {

        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    public JDAnalysisResponse analyze(Long userId, String jd) {

        // User
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // User Skills
        List<Skill> userSkills = skillRepository.findByUser(user);

        Set<String> userSkillSet = new HashSet<>();

        for (Skill skill : userSkills) {
            userSkillSet.add(skill.getSkillName().toLowerCase());
        }

        // Common Skills
        List<String> availableSkills = Arrays.asList(
                "java",
                "spring boot",
                "mysql",
                "hibernate",
                "rest api",
                "git",
                "github",
                "docker",
                "aws",
                "microservices",
                "html",
                "css",
                "javascript",
                "react",
                "angular",
                "sql",
                "mongodb",
                "kubernetes"
        );

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();
        List<String> atsKeywords = new ArrayList<>();
        List<String> improvementSuggestions = new ArrayList<>();
        List<String> recommendations = new ArrayList<>();

        String lowerJD = jd.toLowerCase();

        for (String skill : availableSkills) {

            if (lowerJD.contains(skill)) {

                atsKeywords.add(skill);

                if (userSkillSet.contains(skill)) {

                    matchedSkills.add(skill);

                } else {

                    missingSkills.add(skill);

                    improvementSuggestions.add(
                            "Add " + skill + " in your resume if you have worked on it."
                    );

                    recommendations.add(
                            "Learn " + skill + " to improve your profile."
                    );
                }
            }
        }

        int total = matchedSkills.size() + missingSkills.size();

        int percentage = total == 0 ? 0
                : (matchedSkills.size() * 100) / total;

        JDAnalysisResponse response = new JDAnalysisResponse();

        response.setMatchedSkills(matchedSkills);
        response.setMissingSkills(missingSkills);
        response.setKeywordSuggestions(atsKeywords);

        return response;
    }
}