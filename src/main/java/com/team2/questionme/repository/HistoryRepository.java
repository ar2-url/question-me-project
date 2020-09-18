/*
package com.team2.questionme.repository;

import com.team2.questionme.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class HistoryRepository {
    private EntityManager entityManager;

    @Autowired
    public HistoryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<String> findByUserOrderByIdDesc(User user) {

        String query = "" +
                "SELECT ID, NAME, SURNAME, AGE\n" +
                "FROM JDBC_SCHEMA.PERSONS";

        return query;
    }
}
*/
