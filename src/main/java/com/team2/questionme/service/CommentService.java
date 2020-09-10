package com.team2.questionme.service;

import com.team2.questionme.dto.AddCommentDTO;
import com.team2.questionme.model.Answer;
import com.team2.questionme.model.Comment;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.AnswerRepository;
import com.team2.questionme.repository.CommentRepository;
import com.team2.questionme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommentService {

    private UserRepository userRepository;
    private AnswerRepository answerRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(UserRepository userRepository, AnswerRepository answerRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.commentRepository = commentRepository;
    }

    public void addComment(AddCommentDTO addCommentDTO, Long answerId) {
        User testUser = new User("TestName","TestPassword","test@com","test Display Name");
        userRepository.save(testUser);

        Answer answer = answerRepository.getOne(answerId);
        Comment comment = new Comment(6L,answer,testUser,addCommentDTO.getContent(),LocalDate.now() );
        commentRepository.save(comment);
    }
}
