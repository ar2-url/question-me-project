package com.team2.questionme.dto;

import java.time.LocalDate;
import java.util.List;

public class AnswersWithCommentsDTO {

    private Long Id;
    private UserDTO answerer;
    private String contents;
    private LocalDate localDate;
    private Long rating;

    private List<CommentsDTO> commentsDTO;

    public AnswersWithCommentsDTO(Long id, UserDTO answerer, String contents, LocalDate localDate, Long rating, List<CommentsDTO> commentsDTO) {
        Id = id;
        this.answerer = answerer;
        this.contents = contents;
        this.localDate = localDate;
        this.rating = rating;
        this.commentsDTO = commentsDTO;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public UserDTO getAnswerer() {
        return answerer;
    }

    public void setAnswerer(UserDTO answerer) {
        this.answerer = answerer;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public List<CommentsDTO> getCommentsDTO() {
        return commentsDTO;
    }

    public void setCommentsDTO(List<CommentsDTO> commentsDTO) {
        this.commentsDTO = commentsDTO;
    }
}
