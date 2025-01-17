package com.hd.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class OrderInfoController {

    @RequestMapping("/actuator/info")
    public String info() {
        return "redirect:/swagger-ui/index.html";
    }
}
