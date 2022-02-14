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

   public List<OrderItem> review_auth(Long pdct_code, String user_id){
          Query query=  em.createQuery("select d from OrderItem d join d.order where d.order.user.user_id =:user_id and d.product.pdct_code=:pdct_code", OrderItem.class).setParameter("user_id", user_id).setParameter("pdct_code", pdct_code);
          return query.getResultList();

   }

}
