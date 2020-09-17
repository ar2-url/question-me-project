package com.team2.questionme.repository;

import com.team2.questionme.dto.QuestionDTO;
import com.team2.questionme.dto.QuestionHistoryDTO;
import com.team2.questionme.dto.QuestionWithAnswersAndCommentsDTO;
import com.team2.questionme.model.Question;
import com.team2.questionme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<QuestionDTO> findByCategory(String category);

    QuestionWithAnswersAndCommentsDTO getOneById(Long questionId);

    List<QuestionHistoryDTO> findByUserOrderByIdDesc(User user);

}
