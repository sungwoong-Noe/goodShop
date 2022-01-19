package com.goodshop.demo.repository;

import com.goodshop.demo.domain.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String userName;
    private OrderStatus orderStatus;


}
