package com.goodshop.demo.user;

import com.goodshop.demo.domain.user.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
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
    
    @NotEmpty(message = "주소를 입력해주세요")
    private String city;
    
    @NotEmpty(message = "상세주소를 입력해주세요")
    private String street;
    
    @NotEmpty(message = "주소를 입력해주세요")
    private String zipcode;




}
