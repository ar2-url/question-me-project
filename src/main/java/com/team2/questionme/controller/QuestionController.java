package com.team2.questionme.controller;

import com.team2.questionme.dto.AddQuestionDTO;
import com.team2.questionme.dto.QuestionWithAnswersAndCommentsDTO;
import com.team2.questionme.service.QuestionService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addQuestion(
            @RequestBody AddQuestionDTO addQuestionDTO,
            @AuthenticationPrincipal UserDetails userDetails) {
        questionService.addQuestion(addQuestionDTO, userDetails);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("{questionId}")
    @ApiResponses(@ApiResponse(code = 404, message = "Not found"))
    public ResponseEntity<QuestionWithAnswersAndCommentsDTO> fullQuestion(
            @PathVariable Long questionId) {
        Optional<QuestionWithAnswersAndCommentsDTO> q = questionService.getById(questionId);
        if(q.isPresent() == false){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(q.get(), HttpStatus.OK);
    }
}
