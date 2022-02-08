package com.goodshop.demo.domain.product;

import com.goodshop.demo.domain.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="review")
@Getter @Setter
public class ProductReview {

    @Id @GeneratedValue
    @Column(name="review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pdct_code")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "o_code")
    private Order order;

    private String review_title;

    private String review_content;




}
