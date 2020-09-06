package com.team2.questionme.dto;

public class AddQuestionDTO {
    private String content;
    private String category;


    public AddQuestionDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
