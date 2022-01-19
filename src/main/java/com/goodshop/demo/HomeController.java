package com.goodshop.demo;

import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);

        if(session == null){
            return "login/login";
        }

        User loginUser = (User)session.getAttribute(SessionConst.Login_User);



        if(loginUser == null){
            return "login/login";
        }

        System.out.println(loginUser.getUser_id());
        model.addAttribute("login", loginUser);

        return "index";
    }

}
