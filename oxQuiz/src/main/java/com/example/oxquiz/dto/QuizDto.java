package com.example.oxquiz.dto;

import com.example.oxquiz.entity.BaseEntity;
import com.example.oxquiz.entity.Quiz;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class QuizDto extends BaseEntity {
    private Long id;
    @NotBlank(message = "공백 일 수 없습니다.")
    private String description;
    private boolean answer;
    private String writer;

    public QuizDto(Long id, String description, boolean answer, String writer,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        super();
        this.id = id;
        this.description = description;
        this.answer = answer;
        this.writer = writer;
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

    public static QuizDto fromEntity(Quiz quiz){
        return new QuizDto(
                quiz.getId(),
                quiz.getDescription(),
                quiz.isAnswer(),
                quiz.getWriter(),
                quiz.getCreatedAt(),
                quiz.getUpdatedAt()
        );
    }

    public static Quiz fromDto(QuizDto quizDto){
        return new Quiz(
                quizDto.getId(),
                quizDto.getDescription(),
                quizDto.isAnswer(),
                quizDto.getWriter(),
                quizDto.getCreatedAt(),
                quizDto.getUpdatedAt()
        );
    }
}
