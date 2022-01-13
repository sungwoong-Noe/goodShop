package com.goodshop.demo.repository;


import com.goodshop.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {


    //Entity매니저를 주입해준다
    private final EntityManager em;

    //유저 등록
    public void save(User user){
        user.setRegDate(LocalDateTime.now());
        em.persist(user);
    }

    //user 조회
    public User findOne(String user_id){
        return em.find(User.class, user_id);
    }

    //전체 유저 조회
    public List<User> findAll(){
        return em.createQuery("select m from User m", User.class)
                .getResultList();
    }

    //이름으로 회원조회
    public List<User> findByName(String name){
        return em.createQuery("select m from User m where m.user_name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }
}
