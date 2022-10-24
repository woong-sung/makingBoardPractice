package com.jojo.Board.Answer;

import com.jojo.Board.Exception.DataNotFoundException;
import com.jojo.Board.Question.Question;
import com.jojo.Board.Question.QuestionForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);

        //todo
//        answer.setMember();
        this.answerRepository.save(answer);
    }

    public void delete(Long id){
        this.answerRepository.deleteById(id);
    }

    public Question findQuestion(Long id){
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get().getQuestion();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }
}
