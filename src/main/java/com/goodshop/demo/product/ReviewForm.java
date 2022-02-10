package com.goodshop.demo.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ReviewForm {
    
    private String user_id;
    private Long pdct_code;
    
    @NotEmpty(message = "리뷰 제목을 입력해주세요")
    private String r_title;
    
    @NotEmpty(message = "리뷰 내용을 입력해주세요")
    private String r_content;
    
    @NotNull(message = "별점을 입력해주세요")
    private int grade;
    
    
}
