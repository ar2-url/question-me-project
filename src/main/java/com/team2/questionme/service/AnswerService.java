package com.team2.questionme.service;


import com.team2.questionme.dto.AddAnswerDTO;
import com.team2.questionme.dto.AnswerHistoryDTO;
import com.team2.questionme.model.Answer;
import com.team2.questionme.model.Question;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.AnswerRepository;
import com.team2.questionme.repository.QuestionRepository;
import com.team2.questionme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private AnswerRepository answerRepository;


    @Autowired
    public AnswerService(QuestionRepository questionRepository, UserRepository userRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
    }

    public void addAnswer(AddAnswerDTO addAnswerDTO, Long questionId, UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        Question question = questionRepository.getOne(questionId);
        Answer answer = new Answer(user, addAnswerDTO.getContent());
        question.addAnswer(answer);
        questionRepository.save(question);
    }

    public void addPositiveVote(Long answerId, UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        Answer answer = answerRepository.getOne(answerId);
        answer.uprate(user.getId());
        answerRepository.save(answer);
    }

    public void addNegativeVote(Long answerId, UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        Answer answer = answerRepository.getOne(answerId);
        answer.downrate(user.getId());
        answerRepository.save(answer);
    }

    public List<AnswerHistoryDTO> getAllAnswersForUser(UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        return answerRepository.findAnswersForUser();
    }
}
