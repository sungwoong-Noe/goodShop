package com.goodshop.demo.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter
public class UserForm {

    @NotEmpty(message = "아이디 입력은 필수 입니다.")
    private String user_id;

    @Size(min= 8, message = "비밀번호는 최소 8자 이상 입력해주세요")
    private String user_passwd;

    @NotEmpty(message = "이름은 필수입니다.")
    private String user_name;

    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String user_email;

    private String user_phone;

    private String city;
    private String street;
    private String zipcode;


}
