package com.team2.questionme.dto;

import com.team2.questionme.model.Answer;
import com.team2.questionme.model.User;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class CommentsDTO {

    private Long Id;
    private UserDTO userDTO;
    private String contents;
    private LocalDate localDate;

    public CommentsDTO(Long id, UserDTO userDTO, String contents, LocalDate localDate) {
        Id = id;
        this.userDTO = userDTO;
        this.contents = contents;
        this.localDate = localDate;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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
}

