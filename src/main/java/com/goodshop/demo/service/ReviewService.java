package com.goodshop.demo.service;


import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.product.ProductReview;
import com.goodshop.demo.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

//    private ReviewRepository reviewRepository;
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

}
