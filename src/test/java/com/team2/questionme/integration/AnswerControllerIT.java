package com.team2.questionme.integration;

import com.team2.questionme.controller.AnswerController;
import com.team2.questionme.controller.QuestionController;
import com.team2.questionme.dto.AddAnswerDTO;
import com.team2.questionme.dto.AddQuestionDTO;
import com.team2.questionme.dto.AnswersWithCommentsDTO;
import com.team2.questionme.dto.QuestionWithAnswersAndCommentsDTO;
import com.team2.questionme.integration.util.RegisteredUserUtil;
import com.team2.questionme.model.User;
import com.team2.questionme.service.AnswerService;
import com.team2.questionme.service.QuestionService;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class AnswerControllerIT {


    @Autowired
    private RegisteredUserUtil registeredUserCreator;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;

    @Test
    void shouldAddNewAnswer_WhenAllInformationCorrect(){
        // given
        User userForQuestion = registeredUserCreator.registerNewUserWith("userForQuestion");
        User userForAnswer = registeredUserCreator.registerNewUserWith("username");
        AnswerController sut = new AnswerController(answerService);

        QuestionController questionController = new QuestionController(questionService);
        String category = "cat";
        String content = "content";

        AddQuestionDTO addQuestionDTO = new AddQuestionDTO();
        addQuestionDTO.setCategory(category);
        addQuestionDTO.setContent(content);
        Long qId = questionController.addQuestion(addQuestionDTO, userForQuestion).getBody();

        AddAnswerDTO request = new AddAnswerDTO();
        request.setContent(content);

        // when
        ResponseEntity<Void> result = sut.addAnswer(request, qId, userForAnswer);

        // then
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        QuestionWithAnswersAndCommentsDTO question = questionController.fullQuestion(qId).getBody();
        List<AnswersWithCommentsDTO> answers = question.getAnswers();
        assertThat(answers, hasSize(1));
        AnswersWithCommentsDTO answer = answers.get(0);
        assertEquals(content, answer.getContents());
        assertEquals(0L, answer.getRating());
        assertEquals(LocalDate.now(), answer.getLocalDate());
        assertThat(answer.getComments(), IsEmptyCollection.empty());
        assertEquals(userForAnswer.getId(), answer.getUser().getId());
        assertEquals(userForAnswer.getDisplayName(), answer.getUser().getDisplayName());
    }

    @Test
    void shouldReturn400_WhenQuestionDoesNotExist(){
        // given
        User user = registeredUserCreator.registerNewUserWith("any");
        AnswerController sut = new AnswerController(answerService);

        // when
        ResponseEntity<Void> response = sut.addAnswer(new AddAnswerDTO(), 500L, user);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldAddPositiveVote_WhenAnswerExist(){
        // given
        User userForQuestion = registeredUserCreator.registerNewUserWith("userForQuestion");
        User userForAnswer = registeredUserCreator.registerNewUserWith("userForAnswer");
        User userForVote = registeredUserCreator.registerNewUserWith("userForVote");
        AnswerController sut = new AnswerController(answerService);

        QuestionController questionController = new QuestionController(questionService);
        String category = "cat";
        String content = "content";

        AddQuestionDTO addQuestionDTO = new AddQuestionDTO();
        addQuestionDTO.setCategory(category);
        addQuestionDTO.setContent(content);
        Long qId = questionController.addQuestion(addQuestionDTO, userForQuestion).getBody();

        AddAnswerDTO request = new AddAnswerDTO();
        request.setContent(content);
        sut.addAnswer(request, qId, userForAnswer);
        Long answerId = questionController.fullQuestion(qId).getBody().getAnswers().get(0).getId();

        // when
        ResponseEntity<Void> result = sut.addPositiveVote("anyQId", answerId, userForVote);
        // then
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        AnswersWithCommentsDTO answer = questionController.fullQuestion(qId).getBody().getAnswers().get(0);
        assertEquals(1L, answer.getRating());
    }

    @Test
    void shouldReturn400_WhenAnswerForPositiveDoesNotExist(){
        // given
        User user = registeredUserCreator.registerNewUserWith("any");
        AnswerController sut = new AnswerController(answerService);

        // when
        ResponseEntity<Void> response = sut.addPositiveVote("anything", 500L, user);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldAddNegativeVote_WhenAnswerExist(){
        // given
        User userForQuestion = registeredUserCreator.registerNewUserWith("userForQuestion");
        User userForAnswer = registeredUserCreator.registerNewUserWith("userForAnswer");
        User userForVote = registeredUserCreator.registerNewUserWith("userForVote");
        AnswerController sut = new AnswerController(answerService);

        QuestionController questionController = new QuestionController(questionService);
        String category = "cat";
        String content = "content";

        AddQuestionDTO addQuestionDTO = new AddQuestionDTO();
        addQuestionDTO.setCategory(category);
        addQuestionDTO.setContent(content);
        Long qId = questionController.addQuestion(addQuestionDTO, userForQuestion).getBody();

        AddAnswerDTO request = new AddAnswerDTO();
        request.setContent(content);
        sut.addAnswer(request, qId, userForAnswer);
        Long answerId = questionController.fullQuestion(qId).getBody().getAnswers().get(0).getId();

        // when
        ResponseEntity<Void> result = sut.addNegativeVote("anyQId", answerId, userForVote);
        // then
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        AnswersWithCommentsDTO answer = questionController.fullQuestion(qId).getBody().getAnswers().get(0);
        assertEquals(-1L, answer.getRating());
    }

    @Test
    void shouldReturn400_WhenAnswerForNegativeDoesNotExist(){
        // given
        User user = registeredUserCreator.registerNewUserWith("any");
        AnswerController sut = new AnswerController(answerService);

        // when
        ResponseEntity<Void> response = sut.addNegativeVote("anything", 500L, user);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}