package com.goodshop.demo.service;


import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.product.ReviewForm;
import com.goodshop.demo.repository.product.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

//
//    public OrderItem review_auth(Long pdct_code, String user_id){
//        List<OrderItem> orderItem = reviewRepository.review_auth(pdct_code, user_id);
//        return
//    }

}
