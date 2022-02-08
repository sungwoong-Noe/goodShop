package com.goodshop.demo.product;

import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.product.ProductReview;
import com.goodshop.demo.repository.ReviewRepository;
import com.goodshop.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    
    private ReviewService reviewService;
    private ReviewRepository reviewRepository;

//    @PostMapping("/item/review")
//    public String review_do(@PathVariable("pdct_code") Long pdct_code, @Valid ReviewForm reviewForm, BindingResult bindingResult){
//
//
//        ProductReview productReview = new ProductReview();
//
//
//        productReview.setReview_title(reviewForm.getReview_title());
//        productReview.setReview_content(reviewForm.getReview_content());
//
//        return
//    }
//
//    @PostMapping("/item/reveiwauth")
//    public String review_auth(ReviewForm reviewForm){
//
//        return ""
//    }

    @ResponseBody
    @PostMapping("/item/{pdctCode}/{userId}/review")
    public OrderItem reviewAuth(@PathVariable("pdctCode") Long pdct_code, @PathVariable("userId") String user_id){

        OrderItem orderItem = reviewRepository.reviewAuth(user_id, pdct_code);
        System.out.println(orderItem);

        return  orderItem;

    }

}
