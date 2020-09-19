package com.team2.questionme.repository;

import com.team2.questionme.dto.AnswerHistoryDTO;
import com.team2.questionme.dto.QuestionDTO;
import com.team2.questionme.dto.QuestionHistoryDTO;
import com.team2.questionme.dto.QuestionWithAnswersAndCommentsDTO;
import com.team2.questionme.model.Question;
import com.team2.questionme.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<QuestionDTO> findByCategory(String category, Pageable pageable);

    QuestionWithAnswersAndCommentsDTO getOneById(Long questionId);

    List<QuestionHistoryDTO> findByUserOrderByIdDesc(User user);

}
