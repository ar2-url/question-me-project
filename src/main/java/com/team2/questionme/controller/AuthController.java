package com.team2.questionme.controller;

import com.team2.questionme.dto.AuthenticationRequest;
import com.team2.questionme.dto.RegisterNewUserDTO;
import com.team2.questionme.repository.UserRepository;
import com.team2.questionme.security.JwtTokenProvider;
import com.team2.questionme.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository users;
    private RegisterUserService registerUserService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository users, RegisterUserService registerUserService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.users = users;
        this.registerUserService = registerUserService;
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {
        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.users.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterNewUserDTO registerNewUserDTO){
        registerUserService.registerNewUser(registerNewUserDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
