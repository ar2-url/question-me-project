package com.team2.questionme.dto;

import java.time.LocalDate;
import java.util.List;

public interface QuestionWithAnswersAndCommentsDTO {

    Long getId();

    String getContents();

    LocalDate getLocalDate();

    String getCategory();

    IUserDTO getUser();

    List<AnswersWithCommentsDTO> getAnswers();
}
