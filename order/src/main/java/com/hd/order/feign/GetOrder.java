package com.hd.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product-service")
public interface GetOrder {

    @GetMapping("/product/get")
    String product();

}
