package com.team2.questionme.dto;

import java.time.LocalDate;

public interface QuestionHistoryDTO {

    Long getId();

    String getContents();

    LocalDate getLocalDate();

    String getCategory();
}
