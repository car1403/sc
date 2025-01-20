package com.hd.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product-service")
public interface GetProduct {

    @GetMapping("/product/get")
    String get();

}
