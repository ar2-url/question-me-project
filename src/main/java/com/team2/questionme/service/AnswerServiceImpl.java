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
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private AnswerRepository answerRepository;


    @Autowired
    public AnswerServiceImpl(QuestionRepository questionRepository, UserRepository userRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
    }

    public boolean addAnswer(AddAnswerDTO addAnswerDTO, Long questionId, UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        Optional<Question> questionOp = questionRepository.findById(questionId);
        if (questionOp.isPresent()) {
            Answer answer = new Answer(user, addAnswerDTO.getContent());
            Question question = questionOp.get();
            question.addAnswer(answer);
            questionRepository.save(question);
            return true;
        }
        return false;
    }

    public boolean addPositiveVote(Long answerId, UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        Optional<Answer> answerOp = answerRepository.findById(answerId);
        if (answerOp.isPresent()) {
            Answer answer = answerOp.get();
            answer.uprate(user.getId());
            answerRepository.save(answer);
            return true;
        }
        return false;
    }

    public boolean addNegativeVote(Long answerId, UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        Optional<Answer> answerOp = answerRepository.findById(answerId);
        if (answerOp.isPresent()) {
            Answer answer = answerOp.get();
            answer.downrate(user.getId());
            answerRepository.save(answer);
            return true;
        }
        return false;
    }

    public List<AnswerHistoryDTO> getAllAnswersForUser(UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        return answerRepository.findAnswersForUser(user.getId());
    }
}
