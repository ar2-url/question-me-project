package com.team2.questionme.service;

import com.team2.questionme.dto.AddCommentDTO;
import com.team2.questionme.model.Answer;
import com.team2.questionme.model.Comment;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.AnswerRepository;
import com.team2.questionme.repository.CommentRepository;
import com.team2.questionme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    public void addComment(AddCommentDTO addCommentDTO, Long answerId, UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        Answer answer = answerRepository.getOne(answerId);
        Comment comment = new Comment(answer,user,addCommentDTO.getContent());
        commentRepository.save(comment);
    }
}
