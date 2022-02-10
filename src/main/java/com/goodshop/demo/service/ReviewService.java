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

    public int r_auth(ReviewForm reviewForm){

       return  reviewRepository.r_auth(reviewForm.getPdct_code(), reviewForm.getUser_id());
    }

    public OrderItem test(Long pdct_code,String user_id) {

        return reviewRepository.test2(pdct_code, user_id);

    }

    public OrderItem test3(Long pdct_code, String user_id){

        return reviewRepository.test3(pdct_code).filter(r -> r.getOrder().getUser().getUser_id().equals(user_id)).orElse(null);

    }


}
