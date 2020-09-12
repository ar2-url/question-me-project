package com.team2.questionme.service;

import com.team2.questionme.dto.AddQuestionDTO;
import com.team2.questionme.model.Question;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.QuestionRepository;
import com.team2.questionme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
}
