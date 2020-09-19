package com.team2.questionme.dto;

import java.time.LocalDate;

public interface CommentHistoryDTO {

    Long getCommentId();

    String getContents();

    LocalDate getLocalDate();

    Long getQuestionId();
}
