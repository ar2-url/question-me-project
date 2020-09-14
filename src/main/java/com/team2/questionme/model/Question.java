package com.team2.questionme.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;

    @Autowired
    public Question(User user, String text, String category) {
        this.user = user;
        this.contents = text;
        this.localDate = LocalDate.now();
        this.category = category.trim().toLowerCase();
        this.answers = new ArrayList<>();
    }

    protected Question() {
    }

    public void addAnswer(Answer answer){
        this.answers.add(answer);
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
