package com.goodshop.demo.question;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class QuestionForm {

    private String user_id;

    private Long pdct_code;

    @NotEmpty(message = "제목을 입력해주세요")
    private String q_title;

    @Size(min = 10, message = "내용은 10글자 이상으로 작성해주세요")
    private String q_content;

}
