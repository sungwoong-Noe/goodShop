package com.goodshop.demo.service;

import com.goodshop.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    /**
     * 주문
     */
    /*@Transactional
    public Long order(Long userId, Long pdctCode, int count){

    }*/

    //취소

    //검색

}
