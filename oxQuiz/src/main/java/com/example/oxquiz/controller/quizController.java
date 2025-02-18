package com.example.oxquiz.controller;

import com.example.oxquiz.dto.QuizDto;
import com.example.oxquiz.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/quiz")
@Slf4j
@RequiredArgsConstructor
public class quizController {
    private final QuizService quizService;

    //퀴즈 목록 표시
    @GetMapping("")
    public String showQuiz(){
        return "showQuiz";
    }
    //퀴즈 등록
    @PostMapping ("/insert")
    public String quizInsert(@Valid QuizDto dto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        //받은 dto를 서비스에 넘겨주고 저장
        if(bindingResult.hasErrors()){
            log.info("================== valiError ==================");
            return "insertMember";
        }
        quizService.saveQuiz(dto);
        redirectAttributes.addFlashAttribute("msg","신규 데이터 입력");

        return "redirect:/member/view";
    }
    //퀴즈 수정 폼 보이기
    @GetMapping("/{id}")
    public String quizUpdateForm(){
        return "showQuiz";
    }

    //퀴즈 수정
    @PostMapping ("/update")
    public String quizUpdate(){
        return "showQuiz";
    }

    //퀴즈 삭제
    @PostMapping ("/delete")
    public String quizDelete(){
        return "showQuiz";
    }

    //퀴즈 풀이화면 표시
    @GetMapping("/play")
    public String quizPlay(){
        return "showQuiz";
    }

    //퀴즈 답 체크
    @PostMapping ("/check")
    public String quizCheck(){
        return "showQuiz";
    }
}
