package com.goodshop.demo.domain.product;

import com.goodshop.demo.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue
    @Column(name="pdct_code")
    private Long pdct_code;

    //상품이름
    private String pdct_name;

    //상품 가격
    private int pdct_price;

    //상품 재고
    private int pdct_quantity;

    //상품 이미지
    private String pdct_image;

    //상세설명
    private String pdct_detail;

    //등록일
    private LocalDateTime pdct_date;

    //상품 구매수
    private int pdct_sell;

    @ManyToMany(mappedBy = "products")
    private List<Category> categories = new ArrayList<>();

}
