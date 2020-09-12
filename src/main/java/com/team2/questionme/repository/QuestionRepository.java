package com.team2.questionme.repository;

import com.team2.questionme.dto.QuestionDTO;
import com.team2.questionme.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    /**
     * This method sorts desc by id desc because in our system new questions will always have higher id.
     * @param category Questions for this category only will be found
     * @return List<QuestionDTO> Ordered list of questions for given category. Newest are on top.
     */
    @Query("select new com.team2.questionme.dto.QuestionDTO(q.Id, q.contents, q.localDate, q.category, q.user.id, q.user.displayName) from Question q where q.category = :category order by q.Id desc")
    List<QuestionDTO> getFor(String category);
}
