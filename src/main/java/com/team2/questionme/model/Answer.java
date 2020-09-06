package com.team2.questionme.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Question question;

    private String contents;
    private LocalDate localDate;
    private Long rating;

    @Autowired
    public Answer(Long id, User user, Question question, String contents, LocalDate localDate, Long rating) {
        Id = id;
        this.user = user;
        this.question = question;
        this.contents = contents;
        this.localDate = localDate;
        this.rating = rating;
    }
    protected Answer() {
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
