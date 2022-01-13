package com.goodshop.demo.domain.user;

import com.goodshop.demo.domain.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id
    @Column(name="user_id")
    private String user_id;

    //회원 비밀번호
    private String user_passwd;

    //회원이름
    private String user_name;
    
    //회원 전화번호
    private String user_phone;
    
    //회원 이메일
    private String user_email;
    
    //회원가입일
    private LocalDateTime regDate;

    //주소 임베디드 타입
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user")   //읽기 전용, Orders에서 수정해야 외래키 값이 수정된다.
    private List<Order> orders = new ArrayList<>();

}
