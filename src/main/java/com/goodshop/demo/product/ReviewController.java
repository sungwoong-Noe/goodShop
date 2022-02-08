package com.goodshop.demo.product;

import com.goodshop.demo.domain.product.ProductReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ReviewController {

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

}
