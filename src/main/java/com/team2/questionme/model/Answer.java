package com.team2.questionme.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne
    private User user;

    private String contents;
    private LocalDate localDate;
    private Long rating;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Autowired
    public Answer(User user, String contents) {
        this.user = user;
        this.contents = contents;
        this.localDate = LocalDate.now();
        this.rating = 0L;
        this.comments = new ArrayList<>();
    }
    protected Answer() {
    }

    public Long getId() {
        return Id;
    }

//    public void setId(Long id) {
//        Id = id;
//    }

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

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
