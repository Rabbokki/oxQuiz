package com.example.oxquiz.dto;

import com.example.oxquiz.entity.BaseEntity;
import com.example.oxquiz.entity.Member;
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
public class MemberDto extends BaseEntity {
    private Long id;
    @NotBlank(message = "아이디는 필수")
    private String memberId;
    @NotBlank(message = "비밀번호는 필수")
    private String password;

    private boolean status;
    private int answerTrue=0;
    private int answerFalse=0;

    public MemberDto(Long id, String memberId, String password,
                     boolean status, int answerTrue, int answerFalse,
                     LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.memberId = memberId;
        this.password = password;
        this.status = status;
        this.answerTrue = answerTrue;
        this.answerFalse = answerFalse;
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

    public static MemberDto fromEntity(Member member){
        return new MemberDto(
                member.getId(),
                member.getMemberId(),
                member.getPassword(),
                member.isStatus(),
                member.getAnswerTrue(),
                member.getAnswerFalse()
        );
    }

    public static Member fromDto(MemberDto memberDto){
        return new Member(
                memberDto.getId(),
                memberDto.getMemberId(),
                memberDto.getPassword(),
                memberDto.isStatus(),
                memberDto.getAnswerTrue(),
                memberDto.getAnswerFalse()
        );
    }
}
