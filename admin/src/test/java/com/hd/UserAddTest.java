package com.hd;

import com.hd.login.entity.Authority;
import com.hd.login.entity.Users;
import com.hd.login.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@SpringBootTest
class UserAddTest {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
        Users user = Users.builder()
                .email("admin@naver.com")
                .password(passwordEncoder.encode("111111"))
                .roles(Collections.singletonList(Authority.ROLE_ADMIN.name()))
                .build();
        usersRepository.save(user);
    }


}
