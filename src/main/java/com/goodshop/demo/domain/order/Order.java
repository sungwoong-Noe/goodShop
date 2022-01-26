package com.goodshop.demo.domain.order;

import com.goodshop.demo.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="order_info")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name="o_code")
    private Long o_code;

    //다대일 관계
    //회원id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    //주문 상품
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
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
        orderItem.setOrder(this);
    }

    //주문시간
    private LocalDateTime orderDate;




    //생성 메서드
    /**
     * 주문 생성을 여기서 완성 짓는다, 유저 정보, 상품 정보
     */
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

    //비지니스 로직
    /**
     * 주문 취소
     */
    public void cancel(){
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }

    //조회로직
    /**
     * 전체 주문가격 조회
     */
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
