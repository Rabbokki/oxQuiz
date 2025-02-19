package com.example.oxquiz.service;

import com.example.oxquiz.dto.QuizDto;
import com.example.oxquiz.entity.Quiz;
import com.example.oxquiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    public void saveQuiz(QuizDto dto) {
        Quiz quiz = QuizDto.fromDto(dto);
        quizRepository.save(quiz);
    }

    public List<QuizDto> findAllQuizs() {
        List<Quiz> quizList = quizRepository.findAll();
        return quizList.stream().map(x->QuizDto.fromEntity(x)).toList();
    }

    public QuizDto findById(Long id) {
        return quizRepository.findById(id).stream().map(x->QuizDto.fromEntity(x)).findAny().orElse(null);
    }

    public Quiz updateQuiz(QuizDto dto) {
        Quiz quiz = quizRepository.findById(dto.getId()).orElseThrow(()->new IllegalArgumentException("퀴즈 없음" + dto
                .getId()));
        quiz.update(dto.getDescription(),dto.isAnswer(),dto.getWriter());
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    public QuizDto quizPlay(){
        List<Object[]> resultList = quizRepository.randomQuiz();
        if (resultList.isEmpty()) {
            System.out.println("퀴즈 없음");
        }
        Object[] result = resultList.get(0);
        System.out.println("Result from database: " + Arrays.toString(result));
        return new QuizDto(
                (Long) result[2],
                (String) result[4],
                (Boolean) result[0],
                (String) result[5]
        );
    }
}
