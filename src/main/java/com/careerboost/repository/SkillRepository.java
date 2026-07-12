package com.careerboost.repository;

import com.careerboost.entity.Skill;
import com.careerboost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findByUser(User user);

    boolean existsByUserAndSkillName(User user, String skillName);

    void deleteByUser(User user);

}