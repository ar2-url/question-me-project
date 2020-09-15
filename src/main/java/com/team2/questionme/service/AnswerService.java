package com.team2.questionme.service;


import com.team2.questionme.dto.AddAnswerDTO;
import com.team2.questionme.model.Answer;
import com.team2.questionme.model.Question;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.QuestionRepository;
import com.team2.questionme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;

    @Autowired
    public AnswerService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public void addAnswer(AddAnswerDTO addAnswerDTO, Long questionId, UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        Question question = questionRepository.getOne(questionId);
        Answer answer = new Answer(user, addAnswerDTO.getContent());
        question.addAnswer(answer);
        questionRepository.save(question);
    }
}
