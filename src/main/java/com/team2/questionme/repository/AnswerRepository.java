package com.team2.questionme.repository;

import com.team2.questionme.dto.AnswerHistoryDTO;
import com.team2.questionme.model.Answer;
import com.team2.questionme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    String GET_ANSWERS_FOR_USER = "SELECT  a.id as answer_id, a.local_date, a.rating, a.contents, q.id as question_id  FROM question_me.answer as a\n" +
            "join question_me.question_answers as qa\n" +
            "on a.id = qa.answers_id\n" +
            "join question_me.question as q \n" +
            "on q.id = qa.question_id where a.user_id = 1;";
    @Query(GET_ANSWERS_FOR_USER)
    List<Answer> findByUserOrderByIdDesc(final Answer answer);
}