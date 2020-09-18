package com.team2.questionme.dto;

import java.time.LocalDate;
import java.util.List;

public interface AnswerHistoryDTO {

    Long getId();

    String getContents();

    LocalDate getLocalDate();

    List<QuestionDTO> getQuestionId();
}
