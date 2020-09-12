package com.team2.questionme.controller;

import com.team2.questionme.dto.AddCommentDTO;

import com.team2.questionme.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("{questionId}/answers/{answerId}")
    public ResponseEntity addComment(
            @RequestBody AddCommentDTO addCommentDTO,
            @PathVariable Long questionId,
            @PathVariable Long answerId) {
        commentService.addComment(addCommentDTO, answerId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
