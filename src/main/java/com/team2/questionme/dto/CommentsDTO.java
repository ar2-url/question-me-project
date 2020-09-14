package com.team2.questionme.dto;

import com.team2.questionme.model.Answer;
import com.team2.questionme.model.User;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public interface CommentsDTO {

    public Long getId();

    public IUserDTO getUser();

    public String getContents();

    public LocalDate getLocalDate();
}

