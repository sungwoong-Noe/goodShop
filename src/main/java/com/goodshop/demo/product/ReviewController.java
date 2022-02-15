package com.goodshop.demo.product;
import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.repository.order.OrderRepository;
import com.goodshop.demo.repository.product.ReviewRepository;
import com.goodshop.demo.service.ReviewService;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

//    @ResponseBody
//    @PostMapping("/item/review")
//    public List<User> r_auth(@RequestBody ReviewForm reviewForm){
//
////        List<OrderItem> orderItem = reviewRepository.review_auth(reviewForm.getPdct_code(), reviewForm.getUser_id());
//
//
//
//
//        return reviewRepository.auth_test(reviewForm.getPdct_code(), reviewForm.getUser_id());
//    }

    @ResponseBody
    @PostMapping("/test")
    public  List<OrderItem> test(@RequestBody ReviewForm reviewForm, Model model){

        List<OrderItem> orderItems = reviewService.review_test(reviewForm.getPdct_code(), reviewForm.getUser_id());

        return orderItems;
    }

}
