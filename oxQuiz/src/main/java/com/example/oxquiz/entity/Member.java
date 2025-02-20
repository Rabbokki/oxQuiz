package com.example.oxquiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;
    private String password;
    private boolean status;
    private int answerTrue=0;
    private int answerFalse=0;

    public Member(Long id, String memberId, String password,
                  boolean status, int answerTrue, int answerFalse,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        super();
        this.id = id;
        this.memberId = memberId;
        this.password = password;
        this.status = status;
        this.answerTrue = answerTrue;
        this.answerFalse = answerFalse;
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }
}
