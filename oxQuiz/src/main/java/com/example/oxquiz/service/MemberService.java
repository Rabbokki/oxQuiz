package com.example.oxquiz.service;

import com.example.oxquiz.dto.MemberDto;
import com.example.oxquiz.entity.Member;
import com.example.oxquiz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void saveMember(MemberDto dto) {
        Member member = MemberDto.fromDto(dto);
        memberRepository.save(member);
        System.out.println("회원가입 성공");
    }

    public List<MemberDto> findAllMembers(){
        List<Member> memberList = memberRepository.findAll();
        return memberList.stream().map(x->MemberDto.fromEntity(x)).toList();
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public MemberDto findById(Long id){
        return memberRepository.findById(id).stream().map(x->MemberDto.fromEntity(x)).findAny().orElse(null);
    }

    public Member updateMember(MemberDto dto){
        Member member = MemberDto.fromDto(dto);
        return memberRepository.save(member);
    }

    public MemberDto findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId)
                .map(MemberDto::fromEntity).orElse(null);
    }

    public void updateAnswer(Member sessionUser) {
        memberRepository.save(sessionUser);
        memberRepository.save(sessionUser);
        memberRepository.save(sessionUser);
    }
}
