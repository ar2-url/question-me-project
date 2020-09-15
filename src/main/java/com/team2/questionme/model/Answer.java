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
    private Long id;

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

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
