package com.team2.questionme.dto;

import java.time.LocalDate;
import java.util.List;

public class QuestionWithAnswersAndCommentsDTO {

    private Long id;
    private String contents;
    private LocalDate localDate;
    private String category;
    private UserDTO asker;

    private List<AnswersWithCommentsDTO> answers;

    public QuestionWithAnswersAndCommentsDTO(Long id, String contents, LocalDate localDate, String category, UserDTO asker, List<AnswersWithCommentsDTO> answers) {
        this.id = id;
        this.contents = contents;
        this.localDate = localDate;
        this.category = category;
        this.asker = asker;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public UserDTO getAsker() {
        return asker;
    }

    public void setAsker(UserDTO asker) {
        this.asker = asker;
    }

    public List<AnswersWithCommentsDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersWithCommentsDTO> answers) {
        this.answers = answers;
    }
}
