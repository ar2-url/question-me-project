package com.team2.questionme.dto;

public class AddAnswerDTO {
    //czy sam content wystarczy czy trzeba obiekt question
    private String content;

    public AddAnswerDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
