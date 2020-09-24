package com.team2.questionme.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddCommentDTO {

    @NotBlank
    @Size(max = 5000, message = "Message too long! You can send up to 5000 characters")
    private String content;

    public AddCommentDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
