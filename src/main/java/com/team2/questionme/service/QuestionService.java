package com.team2.questionme.service;

import com.team2.questionme.dto.AddQuestionDTO;
import com.team2.questionme.model.Question;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.QuestionRepository;
import com.team2.questionme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void addQuestion(AddQuestionDTO addQuestionDTO){
        User testUser = new User("TestName","TestPassword","test@com","test Display Name");
        userRepository.save(testUser);
        Question question = new Question(testUser,addQuestionDTO.getContent(),LocalDate.now(),addQuestionDTO.getCategory());
        questionRepository.save(question);
    }

}
