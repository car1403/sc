package com.hd.scg.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@Order(-1)
public class GatewayErrorWebExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.error("Handling exception: {}", ex.getMessage(), ex);

        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // HTTP 응답 설정
        if (ex instanceof SecurityException) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        }
        else {
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 예외 메시지 반환
        String errorResponse = "{\"message\": \"" + ex.getMessage() + "\"}";

        // response body 설정
        DataBuffer buffer = exchange.getResponse()
                .bufferFactory()
                .wrap(errorResponse.getBytes(StandardCharsets.UTF_8));

        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}