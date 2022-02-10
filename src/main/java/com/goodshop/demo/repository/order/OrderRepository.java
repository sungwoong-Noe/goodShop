package com.goodshop.demo.repository.order;

import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    //주문 단건 조회
    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    //회원이름으로 조회
    public List<Order> findList(String user_id){

            return em.createQuery("select o from Order o where o.user.user_name =: user_id ",Order.class)
                .setParameter("user_id", user_id)
                .getResultList();
    }



    //주문 상테 검색
//    public List<Order> findAll(OrderSearch orderSearch)  {
//
//        String jpql = "select o from Order o join o.member m";
//        boolean isFirstCondition = true;
}
