package com.team2.questionme.service;

import com.team2.questionme.dto.RegisterNewUserDTO;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.UserRepository;
import com.team2.questionme.security.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService, RegisterUserService {

    private UserRepository users;
    private PasswordService passwordService;

    @Autowired
    public CustomUserDetailsService(UserRepository users, PasswordService passwordService) {
        this.users = users;
        this.passwordService = passwordService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.users.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }

    @Override
    public void registerNewUser(RegisterNewUserDTO registerNewUserDTO) {
        String hashedPassword = passwordService.hashPassword(registerNewUserDTO.getPassword());
        User user = new User(registerNewUserDTO.getName(), hashedPassword, registerNewUserDTO.getEmail(), registerNewUserDTO.getDisplayName());
        users.save(user);
    }
}
