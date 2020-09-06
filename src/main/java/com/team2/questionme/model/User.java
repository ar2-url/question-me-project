package com.team2.questionme.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

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

    public User() {
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

    @NonNull
    public String getPassword() {
        return password;
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
}
