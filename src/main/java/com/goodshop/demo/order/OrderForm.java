package com.goodshop.demo.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class OrderForm {

    private String user_id;

    private Long pdct_code;

    private int quantity;


}
