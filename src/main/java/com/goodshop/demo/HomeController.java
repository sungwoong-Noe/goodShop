package com.goodshop.demo;

import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.repository.ProductRepository;
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
import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ProductRepository productRepository;

    @RequestMapping("/")
    public String home(@SessionAttribute(name = SessionConst.Login_User, required = false) User user, Model model){


            model.addAttribute("product", productRepository.findAll());
            model.addAttribute("login", user);

        return "index";
    }

}
