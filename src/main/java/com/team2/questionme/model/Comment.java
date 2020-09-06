package com.team2.questionme.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne
    private Answer answer;

    @ManyToOne
    private User user;

    private String contents;
    private LocalDate localDate;

    @Autowired
    public Comment(Long id, Answer answer, User user, String contents, LocalDate localDate) {
        Id = id;
        this.answer = answer;
        this.user = user;
        this.contents = contents;
        this.localDate = localDate;
    }

    public Comment() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
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
}
