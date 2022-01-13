package com.goodshop.demo.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class ProductForm {

    //상품이름
    @NotEmpty(message = "상품이름은 필수입니다.")
    private String pdct_name;

    //상품 가격
    @NotEmpty(message = "상품가격은 필수입니다.")
    @Size(min = 1000, message = "최소가격은 1,000원부터 입니다.")
    private int pdct_price;

    //상품 재고
    @NotEmpty(message = "상품재고는 필수입니다.")
    private int pdct_quantity;

    //대표이미지
    @NotEmpty(message = "대표이미지는 필수입니다.")
    private String pdct_image;

    //상세 이미지
    @NotEmpty(message = "상세이미지는 필수입니다.")
    private String detail_image;

    //상세설명
    private String pdct_detail;

    //상품구매수
    private int pdct_sell;

}
