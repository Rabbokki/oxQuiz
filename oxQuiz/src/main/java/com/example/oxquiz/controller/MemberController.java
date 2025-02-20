package com.example.oxquiz.controller;

import com.example.oxquiz.dto.MemberDto;
import com.example.oxquiz.entity.Member;
import com.example.oxquiz.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/login")
    public String loginProcess(Member member, HttpSession session) {
        System.out.println("login Info : " + member);
        if (member.getMemberId() == null || member.getMemberId().trim().isEmpty()) {
            System.out.println("Error: Member ID is null or empty");
            return "redirect:/member/login";
        }
        MemberDto tempUserDto = memberService.findByMemberId(member.getMemberId());
        if (tempUserDto == null) {
            System.out.println("Fetched Member: " + tempUserDto);
            System.out.println("멤버없음");
            return "redirect:/member/login";
        }
        member.setId(tempUserDto.getId());
        Member sessionUser = MemberDto.fromDto(tempUserDto);
        if(!sessionUser.isStatus()){
            System.out.println("로그인이 불가한 아이디입니다.");
            return "redirect:/member/login";
        }

        if(member.getPassword().equals(sessionUser.getPassword()) &&
                member.getMemberId().equals("root")){
            session.setAttribute("admin",sessionUser);
            session.setMaxInactiveInterval(60*30);
            return "redirect:/member/view";
        }
        if (!ObjectUtils.isEmpty(sessionUser)) {

            if (member.getPassword().equals(
                    sessionUser.getPassword())) {
                // 로그인 성공
                // 세션을 생성해서 전달
                // 비밀번호 확인 후
                session.setAttribute("user", sessionUser);
                session.setMaxInactiveInterval(60 * 30);  // 30분
                return "redirect:/quiz/play";
            } else {
                // 로그인 실패
                return "redirect:/member/login";
            }
        } else {
            // 로그인 실패
            return "redirect:/member/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/member/login";
    }
    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("member",new Member());
        return "/member/login";
    }

    @GetMapping("/insertForm")
    public String insertFormView(Model model){
        model.addAttribute("memberDto",new MemberDto());
        return "/member/insertMember";
    }

    @PostMapping("/insert")
    public String insert(
            @Valid MemberDto dto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){
        //받은 dto를 서비스에 넘겨주고 저장
        if(bindingResult.hasErrors()){
            log.info("================== valiError ==================");
            return "/member/insertMember";
        }
        memberService.saveMember(dto);
        redirectAttributes.addFlashAttribute("msg","신규 데이터 입력");

        return "redirect:/member/login";
    }

    @GetMapping("/view")
    public String showMember(Model model){
        List<MemberDto> memberList = memberService.findAllMembers();
        System.out.println(memberList);
        model.addAttribute("list",memberList);
        return "/member/showMember";
    }

    @GetMapping("/delete/{id}")
    public String deleteMemger(@PathVariable("id")Long id,
                               RedirectAttributes redirectAttributes){
        memberService.deleteById(id);
        redirectAttributes.addFlashAttribute("msg","삭제완료");
        return "redirect:/member/view";
    }

    @GetMapping("/update/{id}")
    public String updateFormView(@PathVariable("id")Long id,
                                 Model model){
        MemberDto dto = memberService.findById(id);
        log.info("=============== dto ===============: " + dto);
        model.addAttribute("member",dto);
        return "/member/updateMember";
    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("member") MemberDto member,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "/member/updateMember";
        }
        //수정 요청
        log.info("################### DTO : ###################" + member);
        memberService.updateMember(member);
        //메시지 전송
        redirectAttributes.addFlashAttribute("msg","수정 완료");
        return "redirect:/member/view";
    }
    @PostMapping("/changeStatus/{id}")
    public String changeStatus(@PathVariable("id")Long id){
        MemberDto byId = memberService.findById(id);
        if(byId!=null){
            byId.setStatus(!byId.isStatus());
            memberService.saveMember(byId);
        }

        return "redirect:/member/view";
    }
}
