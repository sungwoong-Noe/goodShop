package com.goodshop.demo.service;

import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.repository.OrderRepository;
import com.goodshop.demo.repository.OrderSearch;
import com.goodshop.demo.repository.ProductRepository;
import com.goodshop.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(String userId, Long pdct_code, int od_quantity){
        User user = userRepository.findOne(userId);
        Product product = productRepository.findOne(pdct_code);

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(product, product.getPdct_price(), od_quantity);

        //주문생성
        Order order = Order.createOrder(user, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 entity 조회
        Order order = orderRepository.findOne(orderId);
        //취소
        order.cancel();
    }

    //검색
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return orderRepository.findAll(orderSearch);
//    }

}
