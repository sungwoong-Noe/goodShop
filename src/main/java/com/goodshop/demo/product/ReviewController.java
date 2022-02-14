package com.goodshop.demo.product;
import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.repository.product.ReviewRepository;
import com.goodshop.demo.service.ReviewService;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    @ResponseBody
    @PostMapping("/item/review")
    public String r_auth(@RequestBody ReviewForm reviewForm){

        List<OrderItem> orderItem = reviewRepository.review_auth(reviewForm.getPdct_code(), reviewForm.getUser_id());

        System.out.println(orderItem.get(0).getId());
        return reviewForm.getUser_id();
    }


}
