package com.team2.questionme.repository;

import com.team2.questionme.dto.AnswerHistoryDTO;
import com.team2.questionme.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {//, JpaSpecificationExecutor<AnswerHistoryDTO> {
    String GET_ANSWERS_FOR_USER = "SELECT a.id as dupa, a.local_date as localDate, a.rating as rating, a.contents as contents, q.id as questionId " +
            " FROM answer as a\n" +
            "join question_answers as qa\n" +
            "on a.id = qa.answers_id\n" +
            "join question as q \n" +
            "on q.id = qa.question_id where a.user_id = 1";

    @Query(value = GET_ANSWERS_FOR_USER, nativeQuery = true)
    List<AnswerHistoryDTO> findAnswersForUser();

}