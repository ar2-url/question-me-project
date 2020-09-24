package com.team2.questionme.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddQuestionDTO {

    @NotBlank
    @Size(max = 50000, message = "Message too long! You can send up to 50000 characters")
    private String content;

    @NotBlank
    @Size(max = 50, message = "Message too long! You can send up to 50 characters")
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
