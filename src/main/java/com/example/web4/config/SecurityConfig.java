package com.example.web4.config;


import com.example.web4.jwt.JwtFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Resource
    private JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/**").permitAll()// Открытые маршруты
                        .anyRequest().authenticated()
                )
                .cors(cors -> {})
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Добавляем фильтр


        return http.build();
    }




}

