package com.hd.scg.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;

@Component
public class PreFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Pre-filter logic
        ServerHttpRequest request = exchange.getRequest();
        System.out.println("Pre-filter applied:"+request.getURI());
        return chain.filter(exchange);
    }
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE; // 필터의 우선순위 설정
    }
}