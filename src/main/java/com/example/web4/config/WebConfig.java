package com.example.web4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Настраиваем для всех маршрутов
                .allowedOrigins("http://localhost:**") // Разрешаем запросы с http://localhost:5173
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешаем эти методы
                .allowedHeaders("*") // Разрешаем любые заголовки
                .allowCredentials(false); // Если используется cookie
    }
}
