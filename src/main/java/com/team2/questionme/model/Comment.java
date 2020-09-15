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
    private Long id;

    @ManyToOne
    private User user;

    private String contents;
    private LocalDate localDate;

    @Autowired
    public Comment(User user, String contents) {
        this.user = user;
        this.contents = contents;
        this.localDate = LocalDate.now();
    }

    protected Comment() {
    }
}
