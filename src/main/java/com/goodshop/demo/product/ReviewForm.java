package com.goodshop.demo.product;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class ReviewForm {

    @NotEmpty(message = "댓글 제목을 입력해주세요")
    private String review_title;

    @Size(min = 10, message = "10글자 이상 입력해주세요")
    private String review_content;

    private Long pdct_code;

    private String user_id;


}
