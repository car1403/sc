package com.hd.scg.filter;

import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class PostFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) throws SecurityException {
        // Post-filter logic
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    ServerHttpResponse response = exchange.getResponse();
                    log.info("Post-filter applied:"+response.getStatusCode());
                    String errorResponse = "{\"message\": \"" + "error" + "\"}";

                    // response body 설정
                    DataBuffer buffer = exchange.getResponse()
                            .bufferFactory()
                            .wrap(errorResponse.getBytes(StandardCharsets.UTF_8));

                    exchange.getResponse().writeWith(Mono.just(buffer));
                }));
    }
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE; // 필터의 우선순위 설정
    }
}