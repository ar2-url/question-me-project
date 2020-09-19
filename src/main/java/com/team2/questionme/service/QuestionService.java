package com.team2.questionme.service;

import com.team2.questionme.dto.*;
import com.team2.questionme.model.Question;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.QuestionRepository;
import com.team2.questionme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public void addQuestion(AddQuestionDTO addQuestionDTO, UserDetails userDetails){
        User user = userRepository.findByName(userDetails.getUsername()).get();
        Question question = new Question(user,addQuestionDTO.getContent(),addQuestionDTO.getCategory());
        questionRepository.save(question);
    }

    public Page<QuestionDTO> getFor(String category, Pageable pageable) {
        return questionRepository.findByCategory(category, pageable);
    }

    public QuestionWithAnswersAndCommentsDTO getById(Long questionId) {
        return questionRepository.getOneById(questionId);
    }

    public List<QuestionHistoryDTO> getAllForUser(UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        return questionRepository.findByUserOrderByIdDesc(user);
    }
}
