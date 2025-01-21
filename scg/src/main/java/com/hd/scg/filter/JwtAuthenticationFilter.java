package com.hd.scg.filter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;


@Component
@Slf4j
public class JwtAuthenticationFilter implements GlobalFilter{

    private static final String[] whitelist = {"/", "/auth/**"};


    @Value("${service.jwt.secret-key}")
    private String secretKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestURI = exchange.getRequest().getURI().getPath();
        if (isLoginCheckPath(requestURI)) {
            log.info("requestURI: {}", requestURI);

            return chain.filter(exchange);
        }
        String token = extractToken(exchange);

        if(token == null || !validateToken(token)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
    private boolean isLoginCheckPath(String requestURI) {
        return PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
    private String extractToken(ServerWebExchange exchange) {
        String athHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if(athHeader != null && athHeader.startsWith("Bearer ")){
            return athHeader.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        try {
            Jws<Claims> claims =
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            log.info("Payload::"+claims.getBody().toString());
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }
}