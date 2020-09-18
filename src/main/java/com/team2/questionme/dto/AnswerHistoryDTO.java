package com.team2.questionme.dto;

import java.time.LocalDate;
import java.util.List;

public interface AnswerHistoryDTO {

    Long getAnswerId();

    String getContents();

    LocalDate getLocalDate();

    Long getRating();

    Long getQuestionId();
}
