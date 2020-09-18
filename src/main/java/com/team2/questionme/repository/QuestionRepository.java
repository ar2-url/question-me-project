package com.team2.questionme.repository;

import com.team2.questionme.dto.AnswerHistoryDTO;
import com.team2.questionme.dto.QuestionDTO;
import com.team2.questionme.dto.QuestionHistoryDTO;
import com.team2.questionme.dto.QuestionWithAnswersAndCommentsDTO;
import com.team2.questionme.model.Question;
import com.team2.questionme.model.Question_;
import com.team2.questionme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<QuestionDTO> findByCategory(String category);

    QuestionWithAnswersAndCommentsDTO getOneById(Long questionId);

    List<QuestionHistoryDTO> findByUserOrderByIdDesc(User user);


   /* public List<String> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<Question> questionRoot = query.from(Question.class);
        query.select(builder.lower(questionRoot.get(Question_.category))).distinct(true);
        List<String> resultList = entityManager.createQuery(query).getResultList();

        return resultList;
    }*/
    List<AnswerHistoryDTO> findAnswersByUserOrderByIdDesc(User user);
}
