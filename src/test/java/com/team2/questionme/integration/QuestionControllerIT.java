package com.team2.questionme.integration;

import com.team2.questionme.controller.QuestionController;
import com.team2.questionme.controller.UserController;
import com.team2.questionme.dto.AddQuestionDTO;
import com.team2.questionme.dto.QuestionWithAnswersAndCommentsDTO;
import com.team2.questionme.dto.RegisterNewUserDTO;
import com.team2.questionme.dto.UserDTO;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.UserRepository;
import com.team2.questionme.service.QuestionService;
import com.team2.questionme.service.RegisterUserService;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class QuestionControllerIT {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private RegisterUserService registerUserService;
    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    void shouldAddNewQuestion_WhenAllInformationCorrect(){
        // given
        UserController userController = new UserController(registerUserService);
        RegisterNewUserDTO dto = new RegisterNewUserDTO();
        String userName = "userName";
        String displayName = "displayName";
        String email = "email@test.com";
        String password = "secure";
        dto.setName(userName);
        dto.setDisplayName(displayName);
        dto.setEmail(email);
        dto.setPassword(password);
        userController.register(dto);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        QuestionController sut = new QuestionController(questionService);
        String category = "cat";
        String content = "content";

        AddQuestionDTO request = new AddQuestionDTO();
        request.setCategory(category);
        request.setContent(content);

        // when
        ResponseEntity<Long> result = sut.addQuestion(request, userDetails);

        // then
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        ResponseEntity<QuestionWithAnswersAndCommentsDTO> questionResponse = sut.fullQuestion(result.getBody());
        assertEquals(HttpStatus.OK, questionResponse.getStatusCode());
        QuestionWithAnswersAndCommentsDTO question = questionResponse.getBody();
        assertEquals(category, question.getCategory());
        assertEquals(content, question.getContents());
        assertEquals(LocalDate.now(), question.getLocalDate());
        UserDTO user = question.getUser();
        assertEquals(((User) userDetails).getId(), user.getId());
        assertEquals(((User) userDetails).getDisplayName(), user.getDisplayName());
        assertThat(question.getAnswers(), IsEmptyCollection.empty());
    }

    @Test
    void shouldReturn404_WhenQuestionDoesNotExist(){
        // given
        QuestionController sut = new QuestionController(questionService);

        // when
        ResponseEntity<QuestionWithAnswersAndCommentsDTO> questionResponse = sut.fullQuestion(500L);

        // then
        assertEquals(HttpStatus.NOT_FOUND, questionResponse.getStatusCode());
    }
}