package com.team2.questionme.controller;

import com.team2.questionme.dto.AddAnswerDTO;
import com.team2.questionme.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class AnswerController {

    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/{questionId}/answers")
    public ResponseEntity addAnswer(@RequestBody AddAnswerDTO addAnswerDTO, @PathVariable Long questionId){
        answerService.addAnswer(addAnswerDTO, questionId);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
