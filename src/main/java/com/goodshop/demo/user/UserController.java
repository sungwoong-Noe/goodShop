package com.goodshop.demo.user;

import com.goodshop.demo.domain.Address;
import com.goodshop.demo.domain.User;
import com.goodshop.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/join")
    public String join_page(Model model){
        model.addAttribute("userForm", new UserForm());
        return "login/join";
    }

    @PostMapping("/join")
    public String join_do(@Valid UserForm form, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "login/join";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        User user = new User();
        user.setAddress(address);

        user.setUser_id(form.getUser_id());
        user.setUser_passwd(form.getUser_passwd());

        user.setUser_name(form.getUser_name());
        user.setUser_email(form.getUser_email());
        user.setUser_phone(form.getUser_phone());

        userService.join(user);

        return "redirect:/";
    }
}
