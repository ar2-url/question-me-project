package com.team2.questionme.controller;

import com.team2.questionme.dto.AddQuestionDTO;
import com.team2.questionme.dto.QuestionWithAnswersAndCommentsDTO;
import com.team2.questionme.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity addQuestion(
            @RequestBody AddQuestionDTO addQuestionDTO,
            @AuthenticationPrincipal UserDetails userDetails) {
        questionService.addQuestion(addQuestionDTO, userDetails);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("{questionId}")
    public ResponseEntity<QuestionWithAnswersAndCommentsDTO> fullQuestion(
            @PathVariable Long questionId) {
        QuestionWithAnswersAndCommentsDTO q = questionService.getById(questionId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }
}
