package com.goodshop.demo.service;

import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderStatus;
import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.user.Address;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.exception.NotEnoughStockException;
import com.goodshop.demo.repository.order.OrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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
        User user = createUser("woong423","홍길동");

        Product product = createProduct("에어팟맥스", 20, 400000);
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
        User user = createUser("woong423", "홍길동");
        Product product = createProduct("에어팟맥스", 10, 100000);

        int orderCount = 2;
        Long orderId = orderService.order(user.getUser_id(), product.getPdct_code(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        Assert.assertEquals("주문 취소시 상태는 Cancel", OrderStatus.CANCEL, getOrder.getStatus());
        Assert.assertEquals("주문시 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, product.getPdct_quantity());
    }


    @Test()
    public void 재고수량초과() throws  Exception{
        //given
        User user = createUser("woong423","홍길동");
        Product product = createProduct("에어팟맥스", 20, 400000);

        int orderCount = 21;

        //when


        //then
        NotEnoughStockException ex = Assertions.assertThrows(NotEnoughStockException.class, () ->{
            orderService.order(user.getUser_id(), product.getPdct_code(), orderCount);
        });
        Assertions.assertEquals(ex.getMessage(), "need more stock");
    }





    private Product createProduct(String pdct_name, int pdct_quantity, int pdct_price) {
        Product product = new Product();
        product.setPdct_name(pdct_name);
        product.setPdct_price(pdct_price);
        product.setPdct_quantity(pdct_quantity);
        em.persist(product);
        return product;
    }

    private User createUser( String userId,String name) {
        User user = new User();
        user.setUser_id(userId);
        user.setUser_name(name);
        user.setAddress(new Address("서울", "강남로", "23333"));
        em.persist(user);
        return user;
    }

}



