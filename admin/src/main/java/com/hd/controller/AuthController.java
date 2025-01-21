package com.hd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class AuthController {

    @RequestMapping("/auth/login")
    public String login(Model model, String proc) {
        if(proc != null)
            model.addAttribute("proc", proc);
        return "login";
    }
    @RequestMapping("/auth/signup")
    public String signup(Model model) {
        return "signup";
    }

}
