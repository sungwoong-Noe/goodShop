package com.goodshop.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="order_info")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name="o_code")
    private Long id;

    //주문일
    private LocalDateTime o_date;    //자바 8부터 지원되는 날짜 클래스

    //다대일 관계
    //회원id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    //주문 상품
    @OneToMany(mappedBy = "o_code")
    private List<OrderItem> orderItems = new ArrayList<>();

    //주문상태 [Order, Cancel]
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //연관관계 메서드
    public void setUser(User user){
        this.user = user;
        user.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setO_code(this);
    }

    //결제수단
    private String o_payment;

    //주문시간
    private LocalDateTime orderDate;


    //생성 메서드
    public static Order createOrder(User user, OrderItem... orderItems){
        Order order = new Order();
        order.setUser(user);
        for(OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

}
