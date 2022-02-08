package com.goodshop.demo.service;


import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.product.ProductReview;
import com.goodshop.demo.repository.OrderRepository;
import com.goodshop.demo.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private ReviewRepository reviewRepository;
    private OrderRepository orderRepository;
//
//    @Transactional
//    public ProductReview savereview(ProductReview productReview){
//
//        OrderItem orderItem = reviewRepository.reviewAuth(productReview);
//
//        if(orderItem != null){
//            reviewRepository.saveReview(productReview);
//            return productReview;
//        }else if(orderItem == null) {
//
//        }
//    }

    public OrderItem orderItems(Long pdct_code, String user_id ){

        return reviewRepository.findOrder(pdct_code)
                .filter(u -> u.getOrder().getUser().getUser_id().equals(user_id))
                .orElse(null);
    }



}
