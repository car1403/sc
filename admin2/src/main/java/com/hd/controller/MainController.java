package com.hd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class MainController {
    @RequestMapping("/")
    public String main(Model model) {
        log.info("Main...");
        return "index";
    }
    @RequestMapping("/error/accessError")
    public String accessError(Model model, String proc) {
        return "accessError";
    }
    @RequestMapping("/info")
    public String info(Model model, String proc) {
        return "info";
    }
    @RequestMapping("/admin")
    public String admin(Model model, String proc) {
        return "admin";
    }

}
