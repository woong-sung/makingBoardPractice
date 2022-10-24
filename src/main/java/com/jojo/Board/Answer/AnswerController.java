package com.jojo.Board.Answer;

import com.jojo.Board.Question.Question;
import com.jojo.Board.Question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    @Valid
    public String createAnswer(Model model, @PathVariable("id") Long id,
                               @Valid AnswerForm answerForm,
                               BindingResult bindingResult) {
        Question question = this.questionService.getQuestion(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        this.answerService.create(question, answerForm.getContent());
        // Todo: 답변을 저장
        return String.format("redirect:/question/detail/%s", id);
    }



    @RequestMapping("/delete/{id}")
    public String deleteAnswer(@PathVariable("id") Long id){
        Question question = answerService.findQuestion(id);
        Long questionId = question.getId();

        this.answerService.delete(id);

        return String.format("redirect:/question/detail/%s", questionId);
    }
}