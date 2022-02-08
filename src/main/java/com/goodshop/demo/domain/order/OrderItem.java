package com.goodshop.demo.domain.order;

import com.goodshop.demo.domain.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="od_code")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pdct_code")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="o_code")
    private Order order;


    //주문가격
    private int od_price;

    //주문수량
    private int od_quantity;

    public OrderItem() {
    }

    //생성 메서드
    public static OrderItem createOrderItem(Product product, int od_price, int od_quantity){
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOd_price(od_price);
        orderItem.setOd_quantity(od_quantity);

        product.removeStock(od_quantity);
        return orderItem;
    }

    //비지니스 로직
    public void cancel() {
        getProduct().addStock(od_quantity);
    }

    public int getTotalPrice() {
        return getOd_price() * getOd_quantity();
    }
}
