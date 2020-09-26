package com.team2.questionme.repository;

import com.team2.questionme.dto.CommentHistoryDTO;
import com.team2.questionme.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = Queries.GET_COMMENTS_FOR_USER, nativeQuery = true)
    List<CommentHistoryDTO> findCommentsForUser(Long id);
}
