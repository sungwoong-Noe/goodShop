package com.goodshop.demo.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class ProductController {

    @GetMapping("/men")
    public String men(){
        return "/menu/men";
    }

    @GetMapping("/men/vanta")
    public String productDetail(){
        return "/menu/product/productEx";
    }

    @GetMapping("/tech")
    public String tech(){
        return "menu/tech";
    }
}
