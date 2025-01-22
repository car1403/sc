package com.hd.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "order-service")
public interface GetOrder {

    @GetMapping("/order/get/{id}")
    Object get(@PathVariable Long id);

    @GetMapping("/order/getall")
    Object getall();

}
