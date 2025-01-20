package com.hd.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${server.port}")
    String serverIp;

    @GetMapping("/get")
    public Object getProduct(){
        return "Product Detail:"+serverIp;
    }
}
