package com.goodshop.demo.repository.product;

import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    @PersistenceContext
    private final EntityManager em;


    public int r_auth(Long pdct_code, String user_id){
        return em.createQuery("select count(o.id) from  OrderItem o join o.order where o.product.pdct_code =: pdct_code and o.order.user.user_id =: user_id", OrderItem.class)
                .setParameter("pdct_code", pdct_code)
                .setParameter("user_id", user_id)
                .getFirstResult();
    }

    public List<OrderItem> test(Long pdct_code){

        return em.createQuery("select o from OrderItem o where o.product.pdct_code =: pdct_code", OrderItem.class)
                .setParameter("pdct_code", pdct_code)
                .getResultList();
    }
}
