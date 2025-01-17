package com.hd.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthService {
    @Value("${spring.application.name}")
    private String isuser;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    private final SecretKey secretKey;

    public AuthService(@Value("${service.jwt.secret-key}")String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public String createToken(String user_id) {
        return Jwts.builder()
                .claim("user_id",user_id)
                .claim("role","admin")
                .setIssuer(isuser)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(secretKey, SignatureAlgorithm.HS256)
            //  .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}
