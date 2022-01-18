package com.goodshop.demo.repository;

import com.goodshop.demo.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    //주문 단건 조회
    public Order findOne(Long id){
        return em.find(Order.class, id);
    }


//    public List<Order> findAll(OrderSearch orderSearch  {
//
//    })
}
