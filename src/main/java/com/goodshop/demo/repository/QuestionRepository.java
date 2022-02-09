package com.goodshop.demo.repository;

import com.goodshop.demo.domain.question.Question;
import com.goodshop.demo.question.QuestionForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepository {

    @PersistenceContext
    private EntityManager em;


    public void save(Question question){
        question.setQ_date(LocalDateTime.now());
        em.persist(question);
    }

    public List<Question> findList(String user_id){
        return em.createQuery("select q from Question q where q.user.user_id =: user_id", Question.class)
                .setParameter("user_id", user_id)
                .getResultList();
    }

    public Question findOne(Long q_code){
        return em.createQuery("select q from Question q where q.id =: q_code", Question.class)
                .setParameter("q_code", q_code)
                .getSingleResult();
    }

}
