package com.team2.questionme.service;

import com.team2.questionme.dto.*;
import com.team2.questionme.model.Question;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Long addQuestion(AddQuestionDTO addQuestionDTO, UserDetails userDetails){
        User user = (User)userDetails;
        Question question = new Question(user,addQuestionDTO.getContent(),addQuestionDTO.getCategory());
        return questionRepository.save(question).getId();
    }

    public Page<QuestionDTO> getFor(String category, Pageable pageable) {
        return questionRepository.findByCategory(category, pageable);
    }

    public Optional<QuestionWithAnswersAndCommentsDTO> getById(Long questionId) {
        return questionRepository.getOneById(questionId);
    }

    public List<QuestionHistoryDTO> getAllForUser(UserDetails userDetails) {
        User user = (User)userDetails;
        return questionRepository.findByUserOrderByIdDesc(user);
    }
}
