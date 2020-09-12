package com.team2.questionme.service;

import com.team2.questionme.dto.RegisterNewUserDTO;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService, RegisterUserService {

    private UserRepository users;

    @Autowired
    public CustomUserDetailsService(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.users.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }

    @Override
    public void registerNewUser(RegisterNewUserDTO registerNewUserDTO) {
        User user = new User(registerNewUserDTO.getName(),registerNewUserDTO.getPassword(),registerNewUserDTO.getEmail(),registerNewUserDTO.getDisplay_name());
        users.save(user);
    }
}
