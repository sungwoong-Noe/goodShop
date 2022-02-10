package com.goodshop.demo.repository.order;

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

    /**
     * 주문 코드로 주문 내역 조회
     * @param o_code
     * @return
     */
    public OrderItem findByOd_code(Long o_code){

        return em.createQuery("select o from OrderItem o where o.order.id =: o_code ", OrderItem.class)
                .setParameter("o_code", o_code)
                .getSingleResult();
    }


    /**
     * 주문정보
     */
    public List<OrderItem> orderItems(String user_id){
        return em.createQuery(
                "select o from OrderItem o Join fetch o.order left join fetch o.product where o.order.user.user_id =: user_id", OrderItem.class)
                .setParameter("user_id", user_id)
                .getResultList();
    }

}
