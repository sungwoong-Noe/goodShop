package com.goodshop.demo.repository.product;

import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    @PersistenceContext
    private final EntityManager em;

   public OrderItem review_auth(Long o_code, Long pdct_code) {
       return em.createQuery("select d from OrderItem d  join  d.order where d.id=:o_code and d.product.pdct_code=:pdct_code", OrderItem.class)
               .setParameter("o_code", o_code).setParameter("pdct_code", pdct_code)
               .getSingleResult();
   }


}
