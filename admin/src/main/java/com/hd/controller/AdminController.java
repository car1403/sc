package com.hd.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/loginimpl")
    public String loginimpl(String id, String pwd, HttpSession session){
        session.setAttribute("loginid", id);
        return "redirect:/";
    }
    @RequestMapping("/logout")
    public String login(HttpSession session){
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }

}
