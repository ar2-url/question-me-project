package com.team2.questionme.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long Id;
    @ManyToOne
    private User user;

    private String contents;
    private LocalDate localDate;
    private String category;

    @Autowired
    public Question(User user, String text, LocalDate localDate, String category) {
        this.user = user;
        this.contents = text;
        this.localDate = localDate;
        this.category = category;
    }

    protected Question() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
