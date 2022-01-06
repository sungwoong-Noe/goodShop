package com.goodshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "/index";
    }


}
