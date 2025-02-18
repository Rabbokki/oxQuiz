package com.example.oxquiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "quiz")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Quiz extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String desc;
    private boolean answer;
    private String writer;

    public Quiz(Long id, String desc, boolean answer, String writer,
                LocalDateTime createdAt, LocalDateTime updatedAt) {
        super();
        this.id = id;
        this.desc = desc;
        this.answer = answer;
        this.writer = writer;
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }
}
