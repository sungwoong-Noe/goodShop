package com.goodshop.demo.repository;

import com.goodshop.demo.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

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


    //주문 상테 검색
//    public List<Order> findAll(OrderSearch orderSearch)  {
//
//        String jpql = "select o from Order o join o.member m";
//        boolean isFirstCondition = true;
}
