package com.hd.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Value("${server.port}")
    String serverIp;

    @GetMapping("/product")
    public Object getProduct(){
        return "Product Detail:"+serverIp;
    }
}
