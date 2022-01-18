package com.goodshop.demo.service;

import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderStatus;
import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.user.Address;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.repository.OrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired EntityManager em;

    @Autowired OrderService orderService;

    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        User user = new User();
        user.setUser_id("woong423");
        user.setUser_name("회원1");
        user.setAddress(new Address("서울", "강남로", "23333"));
        em.persist(user);

        Product product = new Product();
        product.setPdct_name("에어팟맥스");
        product.setPdct_price(400000);
        product.setPdct_quantity(20);
        em.persist(product);
        int order_count = 2;

        //when
        Long orderId = orderService.order(user.getUser_id(), product.getPdct_code(), order_count);


        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품 주문시 상태는 Order", OrderStatus.ORDER, getOrder.getStatus());
        Assert.assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        Assert.assertEquals("주문한 가격은 가격 * 수량이다. ", 400000 * order_count, getOrder.getTotalPrice());
        Assert.assertEquals("주문 수량 만큼 재고가 줄어야 한다. ", 18, product.getPdct_quantity());
    }

    
    @Test
    public void 주문취소() throws  Exception{
        //given

        //when

        //then
    }

    @Test
    public void 재고수량초과() throws  Exception{
        //given

        //when

        //then
    }
}