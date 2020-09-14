package com.team2.questionme.dto;

import java.time.LocalDate;
import java.util.List;

public interface AnswersWithCommentsDTO {

    public Long getId();

    public IUserDTO getUser() ;

    public String getContents();

    public LocalDate getLocalDate() ;

    public Long getRating();

    public List<CommentsDTO> getComments();
}
