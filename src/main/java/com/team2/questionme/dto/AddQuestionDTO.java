package com.team2.questionme.dto;

public class AddQuestionDTO {
    private String contents;
    private String category;


    public AddQuestionDTO() {
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
