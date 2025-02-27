package com.example.oxquiz.repository;

import com.example.oxquiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    @Query(value = "SELECT * FROM quiz.quiz ORDER BY RAND() LIMIT 1", nativeQuery = true)
    List<Object[]> randomQuiz();


}
