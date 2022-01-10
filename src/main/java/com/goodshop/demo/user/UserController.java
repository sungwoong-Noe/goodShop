package com.goodshop.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/join")
    public String join(){
        return "login/join";
    }
}
