package com.team2.questionme.integration;

import com.team2.questionme.controller.TokenController;
import com.team2.questionme.controller.UserController;
import com.team2.questionme.dto.AuthenticationRequest;
import com.team2.questionme.dto.RegisterNewUserDTO;
import com.team2.questionme.repository.UserRepository;
import com.team2.questionme.security.JwtTokenProviderImpl;
import com.team2.questionme.service.RegisterUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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
    RegisterUserService registerUserService;

    @Test
    void shouldReturnToken_WhenUserIsRegistered() {
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
        TokenController sut = new TokenController(authenticationManager, jwtTokenProvider, users);
        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername(userName);
        request.setPassword(password);

        ResponseEntity<String> result = sut.signin(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertFalse(result.getBody().isEmpty());
    }
}