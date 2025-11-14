package com.senaidev.prjMercadoPratico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    // Bean centralizado do BCryptPasswordEncoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // você pode passar um número maior como parâmetro para aumentar a força, ex: new BCryptPasswordEncoder(12)
    }
}
