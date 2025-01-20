package com.hd.auth.controller;

import com.hd.auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/auth/login")
    public ResponseEntity<?> login(@RequestParam String user_id){
        return ResponseEntity.ok(authService.createToken(user_id));
    }
    @GetMapping("/auth/sign")
    public ResponseEntity<?> sign(@RequestParam String user_id){
        return ResponseEntity.ok("Register ID: "+user_id);
    }
    @GetMapping("/auth/logout")
    public ResponseEntity<?> logout(@RequestParam String user_id){
        return ResponseEntity.ok("Logout ID: "+user_id);
    }
    /*
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AuthResponse {
        private String access_token;
    }
    */
}
