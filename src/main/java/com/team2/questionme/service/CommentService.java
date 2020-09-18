package com.team2.questionme.service;

import com.team2.questionme.dto.AddCommentDTO;
import com.team2.questionme.dto.AnswerHistoryDTO;
import com.team2.questionme.dto.CommentHistoryDTO;
import com.team2.questionme.model.Answer;
import com.team2.questionme.model.Comment;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.AnswerRepository;
import com.team2.questionme.repository.CommentRepository;
import com.team2.questionme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Comment comment = new Comment(user, addCommentDTO.getContent());
        Answer answer = answerRepository.getOne(answerId);
        answer.addComment(comment);
        answerRepository.save(answer);
    }

    public List<CommentHistoryDTO> getAllCommentsForUser(UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        return commentRepository.findCommentsForUser(user.getId());
    }
}
