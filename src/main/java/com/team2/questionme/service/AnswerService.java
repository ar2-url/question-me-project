package com.team2.questionme.service;

import com.team2.questionme.dto.AddAnswerDTO;
import com.team2.questionme.dto.AnswerHistoryDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AnswerService {

    boolean addAnswer(AddAnswerDTO addAnswerDTO, Long questionId, UserDetails userDetails);

    void addPositiveVote(Long answerId, UserDetails userDetails);

    void addNegativeVote(Long answerId, UserDetails userDetails);

    List<AnswerHistoryDTO> getAllAnswersForUser(UserDetails userDetails);
}
