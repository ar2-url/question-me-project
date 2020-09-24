package com.team2.questionme.controller;

import com.team2.questionme.dto.RegisterNewUserDTO;
import com.team2.questionme.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private RegisterUserService registerUserService;

    @Autowired
    public UserController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterNewUserDTO registerNewUserDTO) {
        registerUserService.registerNewUser(registerNewUserDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
