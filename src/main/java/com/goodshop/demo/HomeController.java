package com.goodshop.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }


    @GetMapping("/men")
    public String men_Menu(){
        return "menu/men";
    }

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/join")
    public String join(){
        return "login/join";
    }

    @GetMapping("/men/product/vanta")
    public String product(){
        return "menu/product/productEx";
    }
}
