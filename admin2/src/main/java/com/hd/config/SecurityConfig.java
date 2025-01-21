package com.hd.config;


import com.hd.login.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.server.authentication.MaximumSessionsContext;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig  {

    private final  CustomUserDetailsService customUserDetailsService;

    // 단방향 암호화 PWD
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 양방향 암호화 addr, phone

    @Bean
    public StandardPBEStringEncryptor  stringEncoder(@Value("${app.key.algo}") String algo,
                                                   @Value("${app.key.skey}") String skey) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm(algo);
        encryptor.setPassword(skey);
        return encryptor;
    }

    @Bean // Spring Security 인증처리
    protected AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

    // Logout 시 Session 관리
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }

    private static final String[] AUTH_WHITELIST = {
            "/images/**", "/css/**", "/js/**", "/layout/**", "/WEB-INF/views/**", "/auth/**","/error","/"
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //CSRF, CORS
        http.csrf((csrf) -> csrf.disable());
        http.cors(Customizer.withDefaults());

	    http.formLogin(form -> form.loginPage("/auth/login")
                .loginProcessingUrl("/auth/login-process")	// submit 받을 url
                .usernameParameter("userid")
                .passwordParameter("pwd")
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("/");
                })
                .failureHandler((request, response, exception) -> {
                    if(exception instanceof SessionAuthenticationException) {
                        response.sendRedirect("/auth/login?proc=2");
                    }
                    if(exception instanceof BadCredentialsException) {
                        response.sendRedirect("/auth/login?proc=1");
                    }
                })
                .permitAll()
        );
        http.logout(logout -> logout.logoutUrl("/auth/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.sendRedirect("/");
                })
                .invalidateHttpSession(true)
                .deleteCookies("remember-me")
        );

        http.exceptionHandling((exceptionHandling) -> exceptionHandling
                .accessDeniedHandler((request, response, exception) -> {
                    response.sendRedirect("/error/accessError");
                })
        );

        // 권한 규칙 작성
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
        );
        http.sessionManagement((auth)->{
                    auth.maximumSessions(1)
                            .expiredUrl("/")
                            .maxSessionsPreventsLogin(true); // false 이전사용자 로그아웃, true 신규 사용자 로그인 실패
        });

        return http.build();
    }


}