package com.team2.questionme.dto;

import java.time.LocalDate;

public interface CommentsDTO {

    public Long getId();

    public UserDTO getUser();

    public String getContents();

    public LocalDate getLocalDate();
}

