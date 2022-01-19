package com.goodshop.demo.user;

import com.goodshop.demo.domain.user.Address;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.service.UserService;
import com.goodshop.demo.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login_page(@ModelAttribute("loginForm") LoginForm loginForm, Model model){


        return "login/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return "login/login";
        }

        User loginUser = userService.loginForm(loginForm.getUserId(), loginForm.getUserPasswd());

        if(loginUser == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
             return "login/login";
         }

        //로그인 성공 TODO
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.Login_User, loginUser);






        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        System.out.println(request.getRequestedSessionId());
        if(session != null){
            session.invalidate();
        }
        return  "redirect:/";
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
