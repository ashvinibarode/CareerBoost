package com.careerboost.service;

import com.careerboost.entity.Skill;
import com.careerboost.entity.User;
import com.careerboost.repository.SkillRepository;
import com.careerboost.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillService(SkillRepository skillRepository,
                        UserRepository userRepository) {

        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    public List<String> getSkills(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow();

        List<Skill> skills = skillRepository.findByUser(user);

        List<String> result = new ArrayList<>();

        for (Skill skill : skills) {
            result.add(skill.getSkillName());
        }

        return result;
    }

    public void saveSkills(Long userId, List<String> skills) {

        User user = userRepository.findById(userId)
                .orElseThrow();

        skillRepository.deleteByUser(user);

        for (String name : skills) {

            if (name != null && !name.isBlank()) {

                Skill skill = new Skill();

                skill.setSkillName(name.trim());

                skill.setUser(user);

                skillRepository.save(skill);
            }
        }
    }

}