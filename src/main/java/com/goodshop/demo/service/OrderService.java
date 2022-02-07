package com.goodshop.demo.service;

import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(String userId, Long pdct_code, int od_quantity) {

        User user = userRepository.findOne(userId);
        Product product = productRepository.findOne(pdct_code);



        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(product, product.getPdct_price(), od_quantity);

        //주문생성
        Order order = Order.createOrder(user, orderItem);

        orderItem.setOd_price(order.getTotalPrice());

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 entity 조회
        Order order = orderRepository.findOne(orderId);
        //취소
        order.cancel();
    }


    /**
     * 회원 아이디로 주문아이템 조회
     */
    @Transactional
    public List<OrderItem> orderList(String user_id) {

        List<Order> orderList = orderRepository.findList(user_id);
        List<OrderItem> orderItemList = new ArrayList<>();

        for (Order order : orderList) {
            orderItemList.add(orderItemRepository.findByOd_code(order.getId()));
        }

        return orderItemList;
    }

    //검색
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return orderRepository.findAll(orderSearch);
//    }

}