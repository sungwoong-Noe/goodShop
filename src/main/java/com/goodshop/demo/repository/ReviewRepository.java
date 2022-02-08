package com.goodshop.demo.repository;

import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.product.ProductReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private EntityManager em;

    public OrderItem reviewAuth(ProductReview productReview){
        String user_id = productReview.getOrder().getUser().getUser_id();
        Long pdct_code = productReview.getProduct().getPdct_code();
        return em.createQuery("select o from OrderItem o join fetch o.order where o.order.user.user_id =: user_id and  o.product.pdct_code =: pdct_code", OrderItem.class)
                .setParameter("user_id", user_id)
                .setParameter("pdct_code", pdct_code)
                .getSingleResult();
    }

    public void saveReview(ProductReview productReview){
        em.persist(productReview);
    }

    public List<ProductReview> findAll(){
        return em.createQuery("select r from ProductReview r", ProductReview.class)
                .getResultList();
    }

//    public ProductReview findOne(){
////
////    }

}
