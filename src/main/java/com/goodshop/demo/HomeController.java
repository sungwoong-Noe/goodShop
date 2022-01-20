package com.goodshop.demo;

import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(@SessionAttribute(name = SessionConst.Login_User, required = false) User user, Model model){

        if(user == null){
            return "index";
        }

        model.addAttribute("login", user);
        return "index";
    }

}
