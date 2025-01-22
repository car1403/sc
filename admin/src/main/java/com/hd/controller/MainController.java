package com.hd.controller;

import com.hd.service.FeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final FeignService feignService;

    @RequestMapping("/")
    public String main(Model model) {
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
    @GetMapping("/order")
    public String order(Model model) throws IOException {
        model.addAttribute("order", feignService.getOrder(100L));
        model.addAttribute("product", feignService.getProduct());
        return "order";
    }
    @GetMapping("/orderall")
    public String orderall(Model model) throws IOException {
        model.addAttribute("orders", feignService.getOrders());
        return "orderall";
    }
}
