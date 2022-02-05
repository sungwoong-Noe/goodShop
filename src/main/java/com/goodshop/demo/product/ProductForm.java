package com.goodshop.demo.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class ProductForm {

    //상품이름
    @NotEmpty(message = "상품이름은 필수입니다.")
    private String pdct_name;

    //상품코드
    private Long pdct_code;

    //상품 가격
    @NotEmpty(message="상품 가격을 입력해주세요.")
    private int pdct_price;

    //상품 재고
    @NotEmpty(message = "상품 수량을 입력해주세요")
    private int pdct_quantity;

    //대표이미지
    private MultipartFile pdct_image;

    //상세 이미지
    private MultipartFile detail_image;

    //상세설명
    private String pdct_detail;

    //상품구매수
    private int pdct_sell;

    //판매자
    private String seller;


}
