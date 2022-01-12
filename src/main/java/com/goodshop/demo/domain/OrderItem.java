package com.goodshop.demo.domain;

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
    private Order o_code;


    //주문가격
    private int od_price;

    //주문수량
    private int od_quantity;




}
