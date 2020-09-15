package com.team2.questionme.dto;

import java.time.LocalDate;

public interface QuestionDTO {

    Long getId();

    String getContents();

    LocalDate getLocalDate();

    String getCategory();

    UserDTO getUser();
}
