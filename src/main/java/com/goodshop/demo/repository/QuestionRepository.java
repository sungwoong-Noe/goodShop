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

        if(question.getId() == null){
            question.setQ_date(LocalDateTime.now());
            em.persist(question);
        }else{
            question.setQ_date(LocalDateTime.now());
            em.merge(question);
        }


    }

    public List<Question> findList(String user_id){
        return em.createQuery("select q from Question q where q.user.user_id =: user_id", Question.class)
                .setParameter("user_id", user_id)
                .getResultList();
    }

    public List<Question> findPList(Long pdct_code){
        return em.createQuery("select q from Question q where q.product.pdct_code =: pdct_code", Question.class)
                .setParameter("pdct_code", pdct_code)
                .getResultList();
    }

    public Question findOne(Long q_code){
        return em.createQuery("select q from Question q where q.id =: q_code", Question.class)
                .setParameter("q_code", q_code)
                .getSingleResult();
    }


    public void deleteOne(Long q_code){
        Question one = findOne(q_code);
        em.remove(one);
    }

    public List<Question> answerList(Long pdct_code){
        return em.createQuery("select q from Question q where q.product.pdct_code =:pdct_code", Question.class)
                .setParameter("pdct_code", pdct_code)
                .getResultList();
    }

    public int q_cnt(Long pdct_code){
        return em.createQuery("select q from Question q where q.product.pdct_code=:pdct_code", Question.class)
                .setParameter("pdct_code", pdct_code)
                .getResultList().size();
    }

}
