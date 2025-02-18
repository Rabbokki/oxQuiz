package com.example.oxquiz.dto;

import com.example.oxquiz.entity.BaseEntity;
import com.example.oxquiz.entity.Quiz;
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
    private String desc;
    private boolean answer;
    private String writer;

    public QuizDto(Long id, String desc, boolean answer, String writer,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        super();
        this.id = id;
        this.desc = desc;
        this.answer = answer;
        this.writer = writer;
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

    public static QuizDto fromEntity(Quiz quiz){
        return new QuizDto(
                quiz.getId(),
                quiz.getDesc(),
                quiz.isAnswer(),
                quiz.getWriter(),
                quiz.getCreatedAt(),
                quiz.getUpdatedAt()
        );
    }

    public static Quiz fromDto(QuizDto quizDto){
        return new Quiz(
                quizDto.getId(),
                quizDto.getDesc(),
                quizDto.isAnswer(),
                quizDto.getWriter(),
                quizDto.getCreatedAt(),
                quizDto.getUpdatedAt()
        );
    }
}
