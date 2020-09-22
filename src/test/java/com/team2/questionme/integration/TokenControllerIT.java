package com.team2.questionme.integration;

import com.team2.questionme.controller.TokenController;
import com.team2.questionme.dto.AuthenticationRequest;
import com.team2.questionme.integration.util.RegisteredUserUtil;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.UserRepository;
import com.team2.questionme.security.JwtTokenProviderImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
class TokenControllerIT {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProviderImpl jwtTokenProvider;
    @Autowired
    private UserRepository users;

    @Autowired
    private RegisteredUserUtil registeredUserCreator;

    @Test
    void shouldReturnToken_WhenUserIsRegistered() {
        // given
        TokenController sut = new TokenController(authenticationManager, jwtTokenProvider, users);
        String username = "Carolina";
        String pass = "us state";
        registeredUserCreator.registerNewUserWith(username, pass);
        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername(username);
        request.setPassword(pass);

        // when
        ResponseEntity<String> result = sut.signin(request);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertFalse(result.getBody().isEmpty());
    }
}
