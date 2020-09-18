package com.team2.questionme.repository;

import com.team2.questionme.dto.AnswerHistoryDTO;
import com.team2.questionme.dto.CommentHistoryDTO;
import com.team2.questionme.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    String GET_COMMENTS_FOR_USER = "SELECT c.id as commentId, c.local_date as localDate, c.contents as contents, q.id as questionId \n" +
            " FROM comment as c \n" +
            "join answer_comments as ac \n" +
            "on c.id = ac.comments_id \n" +
            "join answer as a \n" +
            "on a.id = ac.answer_id \n" +
            "join question_answers as qa \n" +
            "on qa.answers_id = a.id \n" +
            "join question as q \n" +
            "on q.id = qa.question_id \n" +
            "where c.user_id = ?1 \n" +
            "order by c.id desc";

    @Query(value = GET_COMMENTS_FOR_USER, nativeQuery = true)
    List<CommentHistoryDTO> findCommentsForUser(Long id);
}
