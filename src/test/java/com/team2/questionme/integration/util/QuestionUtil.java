package com.team2.questionme.integration.util;

import com.team2.questionme.controller.QuestionController;
import com.team2.questionme.dto.AddQuestionDTO;
import com.team2.questionme.dto.AnswersWithCommentsDTO;
import com.team2.questionme.model.Answer;
import com.team2.questionme.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionUtil {

    @Autowired
    private QuestionController questionController;

    public Long addNewQuestionBy(User userForQuestion) {
        String category = "cat";
        String content = "content";

        AddQuestionDTO addQuestionDTO = new AddQuestionDTO();
        addQuestionDTO.setCategory(category);
        addQuestionDTO.setContent(content);
        Long qId = questionController.addQuestion(addQuestionDTO, userForQuestion).getBody();
        return qId;
    }

    public Long getAnswerIdFor(Long questionId) {
        return questionController.fullQuestion(questionId).getBody().getAnswers().get(0).getId();
    }

    public AnswersWithCommentsDTO getFirstAnswerFor(Long questionId) {
        return questionController.fullQuestion(questionId).getBody().getAnswers().get(0);
    }
}
