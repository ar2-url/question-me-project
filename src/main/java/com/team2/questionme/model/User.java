package com.team2.questionme.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;
    private String password;
    private String email;
    private String display_name;

    @Autowired
    public User(String name,String password,String email,String display_name) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.display_name = display_name;
    }

    protected User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    @NonNull
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }


    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(@NonNull String display_name) {
        this.display_name = display_name;
    }

    public List<String> getRoles() {
        return new ArrayList<>();
    }
}
