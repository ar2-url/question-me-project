package com.team2.questionme.dto;

import java.time.LocalDate;

public interface QuestionDTO {
    public Long getId() ;

    public String getContents() ;

    public LocalDate getLocalDate() ;

    public String getCategory() ;

    public UserDTO getUser();
}
