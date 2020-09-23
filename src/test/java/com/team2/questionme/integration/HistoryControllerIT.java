package com.team2.questionme.integration;

import com.team2.questionme.controller.AnswerController;
import com.team2.questionme.controller.HistoryController;
import com.team2.questionme.controller.QuestionController;
import com.team2.questionme.dto.AddAnswerDTO;
import com.team2.questionme.dto.AddQuestionDTO;
import com.team2.questionme.dto.AnswerHistoryDTO;
import com.team2.questionme.dto.QuestionHistoryDTO;
import com.team2.questionme.integration.util.IdsDto;
import com.team2.questionme.integration.util.QuestionUtil;
import com.team2.questionme.integration.util.RegisteredUserUtil;
import com.team2.questionme.model.User;
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
class HistoryControllerIT {

    @Autowired
    private RegisteredUserUtil registeredUserCreator;
    @Autowired
    private QuestionUtil questionUtil;
    @Autowired
    private HistoryController sut;
    @Autowired
    private QuestionController questionController;
    @Autowired
    private AnswerController answerController;

    @Test
    void shouldReturnAllQuestionAskedByUser_WhenThereAreQuestionsFromMultipleUsers(){
        // given
        User user = registeredUserCreator.registerNewUserWith("user1");
        User otherUser1 = registeredUserCreator.registerNewUserWith("user2");
        User otherUser2 = registeredUserCreator.registerNewUserWith("user3");

        questionUtil.addNewQuestion(user);
        questionUtil.addNewQuestion(user);
        questionUtil.addNewQuestion(user);
        questionUtil.addNewQuestion(otherUser1);
        questionUtil.addNewQuestion(otherUser1);
        questionUtil.addNewQuestion(otherUser2);
        questionUtil.addNewQuestion(otherUser2);
        questionUtil.addNewQuestion(otherUser2);
        questionUtil.addNewQuestion(otherUser2);

        // when
        ResponseEntity<List<QuestionHistoryDTO>> result = sut.questionsHistory(user);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        List<QuestionHistoryDTO> history = result.getBody();
        assertThat(history, hasSize(3));
    }

    @Test
    void shouldReturnCorrectHistoryDetails_WhenThereAreQuestionsFromMultipleUsers(){
        // given
        User user = registeredUserCreator.registerNewUserWith("user1");

        String category = "cat1";
        String content = "content";
        AddQuestionDTO addQuestionDTO = new AddQuestionDTO();
        addQuestionDTO.setCategory(category);
        addQuestionDTO.setContent(content);
        Long qId = questionController.addQuestion(addQuestionDTO, user).getBody();

        // when
        ResponseEntity<List<QuestionHistoryDTO>> result = sut.questionsHistory(user);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        QuestionHistoryDTO historyItem = result.getBody().get(0);
        assertEquals(qId, historyItem.getId());
        assertEquals(LocalDate.now(), historyItem.getLocalDate());
        assertEquals(category, historyItem.getCategory());
        assertEquals(content, historyItem.getContents());
    }

    @Test
    void shouldReturnAllAnswersByUser_WhenThereAreAnswersFromMultipleUsers(){
        // given
        User user = registeredUserCreator.registerNewUserWith("user1");
        User otherUser1 = registeredUserCreator.registerNewUserWith("user2");
        User otherUser2 = registeredUserCreator.registerNewUserWith("user3");

        AddAnswerDTO anyAnswer = new AddAnswerDTO();
        anyAnswer.setContent("anything");

        IdsDto idsDto1 = questionUtil.addNewQuestionWithAnswer(user, otherUser1);
        answerController.addAnswer(anyAnswer, idsDto1.getQuestionId(), user);
        answerController.addAnswer(anyAnswer, idsDto1.getQuestionId(), otherUser1);
        answerController.addAnswer(anyAnswer, idsDto1.getQuestionId(), otherUser2);

        IdsDto idsDto2 = questionUtil.addNewQuestionWithAnswer(otherUser1, otherUser2);
        answerController.addAnswer(anyAnswer, idsDto2.getQuestionId(), user);
        answerController.addAnswer(anyAnswer, idsDto2.getQuestionId(), otherUser1);

        IdsDto idsDto3 = questionUtil.addNewQuestionWithAnswer(otherUser2, user);
        answerController.addAnswer(anyAnswer, idsDto1.getQuestionId(), user);
        answerController.addAnswer(anyAnswer, idsDto1.getQuestionId(), otherUser1);
        answerController.addAnswer(anyAnswer, idsDto1.getQuestionId(), otherUser2);

        // when
        ResponseEntity<List<AnswerHistoryDTO>> result = sut.answersHistory(user);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        List<AnswerHistoryDTO> history = result.getBody();
        assertThat(history, hasSize(4));
    }

    @Test
    void shouldReturnCorrectHistoryDetails_WhenThereAreAnswersFromMultipleUsers(){
        // given
        User user = registeredUserCreator.registerNewUserWith("user1");
        User otherUser1 = registeredUserCreator.registerNewUserWith("user2");

        Long qId = questionUtil.addNewQuestion(otherUser1);
        String content = "content";
        AddAnswerDTO addAnswerDTO = new AddAnswerDTO();
        addAnswerDTO.setContent(content);
        answerController.addAnswer(addAnswerDTO, qId, user);

        // when
        ResponseEntity<List<AnswerHistoryDTO>> result = sut.answersHistory(user);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        AnswerHistoryDTO historyItem = result.getBody().get(0);
        assertEquals(qId, historyItem.getQuestionId());
        assertEquals(LocalDate.now(), historyItem.getLocalDate());
        assertEquals(0L, historyItem.getRating());
        assertEquals(content, historyItem.getContents());
    }
}