package com.goodshop.demo.domain;


import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter //값타입은 변경이 되면 안된다. getter만 사용
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    //생성할때만 값이 세팅되는것이 좋은 설계
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
