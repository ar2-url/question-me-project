package com.team2.questionme.integration.util;

import com.team2.questionme.controller.AnswerController;
import com.team2.questionme.controller.QuestionController;
import com.team2.questionme.dto.AddAnswerDTO;
import com.team2.questionme.dto.AddQuestionDTO;
import com.team2.questionme.dto.AnswersWithCommentsDTO;
import com.team2.questionme.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionUtil {

    @Autowired
    private QuestionController questionController;
    @Autowired
    private AnswerController answerController;

    public Long addNewQuestion(User userForQuestion) {
        return addNewQuestion(userForQuestion, "category");
    }

    public Long addNewQuestion(User userForQuestion, String category) {
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

    public IdsDto addNewQuestionWithAnswer(User userForQuestion, User userForAnswer) {
        Long qId = addNewQuestion(userForQuestion);

        String content = "content";
        AddAnswerDTO addAnswerDTO = new AddAnswerDTO();
        addAnswerDTO.setContent(content);
        answerController.addAnswer(addAnswerDTO, qId, userForAnswer);
        Long answerId = getAnswerIdFor(qId);

        IdsDto result = new IdsDto();
        result.setAnswerId(answerId);
        result.setQuestionId(qId);

        return result;
    }
}
