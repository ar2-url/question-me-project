package com.team2.questionme.integration;

import com.team2.questionme.controller.CommentController;
import com.team2.questionme.controller.HistoryController;
import com.team2.questionme.controller.QuestionController;
import com.team2.questionme.dto.AddQuestionDTO;
import com.team2.questionme.dto.QuestionHistoryDTO;
import com.team2.questionme.integration.util.QuestionUtil;
import com.team2.questionme.integration.util.RegisteredUserUtil;
import com.team2.questionme.model.User;
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
import static org.junit.jupiter.api.Assertions.*;

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

}