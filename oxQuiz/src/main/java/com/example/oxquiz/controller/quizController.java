package com.example.oxquiz.controller;

import com.example.oxquiz.dto.QuizDto;
import com.example.oxquiz.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/quiz")
@Slf4j
@RequiredArgsConstructor
public class quizController {
    private final QuizService quizService;

    //퀴즈 목록 표시
    @GetMapping("/view")
    public String showQuiz(Model model){
        List<QuizDto> quizDtos = quizService.findAllQuizs();
        System.out.println(quizDtos);
        model.addAttribute("list",quizDtos);
        return "showQuiz";
    }
    @GetMapping("/insert")
    public String quizInsertForm(Model model){
        model.addAttribute("quizDto",new QuizDto());
        return "quizInsert";
    }
    //퀴즈 등록
    @PostMapping ("/insert")
    public String quizInsert(@Valid QuizDto dto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        //받은 dto를 서비스에 넘겨주고 저장
        if(bindingResult.hasErrors()){
            log.info("================== valiError ==================");
            return "quizInsert";
        }
        quizService.saveQuiz(dto);
        redirectAttributes.addFlashAttribute("msg","신규 데이터 입력");

        return "redirect:/quiz/view";
    }
    //퀴즈 수정 폼 보이기
    @GetMapping("/{id}")
    public String quizUpdateForm(@PathVariable("id")Long id,
                                 Model model){
        QuizDto dto = quizService.findById(id);
        model.addAttribute("quiz",dto);
        return "quizUpdate";
    }

    //퀴즈 수정
    @PostMapping ("/update")
    public String quizUpdate(@Valid @ModelAttribute("quiz") QuizDto dto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "quizUpdate";
        }
        quizService.updateQuiz(dto);
        redirectAttributes.addFlashAttribute("msg","퀴즈 변경 완료");
        return "redirect:/quiz/view";
    }

    //퀴즈 삭제
    @GetMapping ("/delete")
    public String quizDelete(@RequestParam("id")Long id, RedirectAttributes redirectAttributes){
        quizService.deleteQuiz(id);
        redirectAttributes.addFlashAttribute("msg","삭제완료");
        return "redirect:/quiz/view";
    }

    //퀴즈 풀이화면 표시
    @GetMapping("/play")
    public String quizPlay(Model model){
        QuizDto quizDto = quizService.quizPlay();
        if(!(quizDto==null)){
            model.addAttribute("randomQuiz",quizDto);
        }else {
            model.addAttribute("noQuiz","등록된 문제가 없습니다.");
        }
        return "quizPlay";
    }

    //퀴즈 답 체크
    @GetMapping ("/check")
    public String quizCheck(Model model, @RequestParam("answer") Boolean answer){
        QuizDto quizDto = quizService.quizPlay();
        boolean check = (quizDto.isAnswer() == answer);
        model.addAttribute("randomQuiz",quizDto);
        model.addAttribute("check",check);
        return "quizCheck";
    }
}
