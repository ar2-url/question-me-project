package com.team2.questionme.repository;

import com.team2.questionme.model.Question;
import com.team2.questionme.model.Question_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CategoryRepository {
    private EntityManager entityManager;

    @Autowired
    public CategoryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<String> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<Question> questionRoot = query.from(Question.class);
        query.select(builder.lower(questionRoot.get(Question_.category))).distinct(true);
        List<String> resultList = entityManager.createQuery(query).getResultList();

        return resultList;
    }
}
