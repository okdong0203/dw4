package com.board.dw4.controller;

import com.board.dw4.domain.UserDto;
import com.board.dw4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService service;

    // 로그인 화면을 보여줌
    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(String id, String pwd, String toURL, HttpServletRequest request) throws Exception{

        // 1. 아이디와 패스워드 확인
        if(!loginCheck(id, pwd)){
            String msg = URLEncoder.encode("ID 또는 패스워드가 일치하지 않습니다.", "UTF-8");

            return "redirect:/login/login?msg="+msg;
        }

        // 2. 아이디와 패스워드가 일치하면 세션객체 얻어오고, 세션객체에 아이디를 저장
        HttpSession session = request.getSession();

        session.setAttribute("id", id);

        toURL = toURL == null|| toURL.equals("") ? "/" : toURL;
        return "redirect:"+toURL;

    }

    public boolean loginCheck(String id, String pwd){
        UserDto userDto = null;

        userDto = service.login(id);

        return userDto != null && userDto.getPwd().equals(pwd);
    }
}


