package com.team2.questionme.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddAnswerDTO {

    @NotBlank
    @Size(max = 50000, message = "Message too long! You can send up to 50000 characters")
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
