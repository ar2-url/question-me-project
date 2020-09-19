package com.team2.questionme.service;

import com.team2.questionme.dto.AddCommentDTO;
import com.team2.questionme.dto.CommentHistoryDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CommentService {

    void addComment(AddCommentDTO addCommentDTO, Long answerId, UserDetails userDetails);

    List<CommentHistoryDTO> getAllCommentsForUser(UserDetails userDetails);

}
