package com.team2.questionme.controller;

import com.team2.questionme.dto.AddAnswerDTO;
import com.team2.questionme.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addAnswer(@RequestBody AddAnswerDTO addAnswerDTO,
                                    @PathVariable Long questionId,
                                    @AuthenticationPrincipal UserDetails userDetails
    ) {
        answerService.addAnswer(addAnswerDTO, questionId, userDetails);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/{questionId}/answers/{answerId}/votes/positives")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addPositiveVote(@PathVariable Long answerId,
                                  @AuthenticationPrincipal UserDetails userDetails
    ) {
        answerService.addPositiveVote(answerId, userDetails);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PostMapping("/{questionId}/answers/{answerId}/votes/negatives")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addNegativeVote(@PathVariable Long answerId,
                                          @AuthenticationPrincipal UserDetails userDetails
    ) {
        answerService.addNegativeVote(answerId, userDetails);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
