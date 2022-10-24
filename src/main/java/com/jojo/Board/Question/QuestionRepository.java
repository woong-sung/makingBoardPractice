package com.jojo.Board.Question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findBySubject(String subject);

    List<Question> findBySubjectLike(String subject);

    //    void deleteById(Long id);
    Page<Question> findAll(Pageable pageable);

}
