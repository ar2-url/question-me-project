package com.team2.questionme.controller;

import com.team2.questionme.dto.AddQuestionDTO;
import com.team2.questionme.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity addQuestion(@RequestBody AddQuestionDTO addQuestionDTO){
        questionService.addQuestion(addQuestionDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
