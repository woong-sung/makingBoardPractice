package com.jojo.Board.Member;

import com.jojo.Board.Answer.Answer;
import com.jojo.Board.Question.Question;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String name;

    @Column
    @NotBlank
    private String password;
//
//    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
//    private List<Question> questionList;
//
//    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
//    private List<Answer> answerList;

}