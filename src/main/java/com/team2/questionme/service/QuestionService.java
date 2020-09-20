package com.team2.questionme.service;

import com.team2.questionme.dto.AddQuestionDTO;
import com.team2.questionme.dto.QuestionDTO;
import com.team2.questionme.dto.QuestionHistoryDTO;
import com.team2.questionme.dto.QuestionWithAnswersAndCommentsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface QuestionService {

    void addQuestion(AddQuestionDTO addQuestionDTO, UserDetails userDetails);

    Page<QuestionDTO> getFor(String category, Pageable pageable);

    QuestionWithAnswersAndCommentsDTO getById(Long questionId);

    List<QuestionHistoryDTO> getAllForUser(UserDetails userDetails);
}
