package com.team2.questionme.repository;

import com.team2.questionme.dto.AnswerHistoryDTO;
import com.team2.questionme.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    String GET_ANSWERS_FOR_USER = "SELECT c.id as commentId, c.local_date as localDate, c.contents as contents, a.id as answerId " +
            " FROM comments as c\n" +
            "join answer_comment as ac\n" +
            "on c.id = ac.comments_id\n" +
            "join answer as a \n" +
            "on a.id = ac.answer_id where a.user_id = ?1\n" +
            "order by a.id desc";

    @Query(value = GET_ANSWERS_FOR_USER, nativeQuery = true)
    List<AnswerHistoryDTO> findCommentsForUser(Long id);
}
