package com.careerboost.repository;

import com.careerboost.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizResultRepository
        extends JpaRepository<QuizResult, Long> {

    List<QuizResult> findByUserIdOrderByAttemptedAtDesc(Long userId);
}