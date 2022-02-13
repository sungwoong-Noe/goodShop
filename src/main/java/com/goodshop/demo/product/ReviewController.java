package com.goodshop.demo.product;
import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.repository.product.ReviewRepository;
import com.goodshop.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
//
//    @ResponseBody
//    @GetMapping("/item/review/{user_id}")
//    public List<OrderItem> r_auth(@PathVariable String user_id){
//
//
//
//
//        return orderItems;
//    }




}
