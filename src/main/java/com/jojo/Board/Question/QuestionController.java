package com.jojo.Board.Question;


import com.jojo.Board.Answer.AnswerForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/question")
@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;


//    @RequestMapping("/list")
//    public String list(Model model) {
//        List<Question> questionList = this.questionService.getList();
//        model.addAttribute("questionList", questionList);
//        return "question_list";
//    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id,
                         AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);

        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    // 목업데이터 만들기
    @GetMapping("/createExample")
    public String questionExampleCreate() {
        for (int i = 0; i < 100; i++) {
            this.questionService.create("테스트 " + i, "테스트 " + i);
        }
        return "redirect:/question/list";//질문 저장후 질문 목록으로 이동
    }

    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";//질문 저장후 질문 목록으로 이동

    }

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Long id) {
        this.questionService.delete(id);

        return "redirect:/question/list";
    }

    // 테스트를 위해 모든 데이터 삭제
    @RequestMapping("/deleteAll")
    public String deleteAllQuestion() {
        this.questionService.deleteAll();

        return "redirect:/question/list";
    }
}