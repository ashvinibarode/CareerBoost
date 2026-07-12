package com.careerboost.repository;

import com.careerboost.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = """
            SELECT * FROM questions
            WHERE technology = :technology
            ORDER BY RAND()
            LIMIT :limit
            """, nativeQuery = true)
    List<Question> findRandomQuestions(
            @Param("technology") String technology,
            @Param("limit") int limit
    );
}