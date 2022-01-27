package com.goodshop.demo.repository;

import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@RequiredArgsConstructor
@Repository
public class OrderItemRepository{

    @PersistenceContext
    private EntityManager em;

//    public List<OrderItem> findByOd_code(Long o_code){
//        return em.createQuery("select o from OrderItem o where o.order.id =: o_code ", OrderItem.class)
//                .setParameter("o_code", o_code)
//                .getResultList();
//    }

    public OrderItem findByOd_code(Long o_code){

        return em.createQuery("select o from OrderItem o where o.order.id =: o_code ", OrderItem.class)
                .setParameter("o_code", o_code)
                .getSingleResult();

    }

}
