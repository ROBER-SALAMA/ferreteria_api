package com.example.ferreteria_api.global;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // Permite cualquier origen (solo desarrollo)
                        .allowedMethods("*") // GET, POST, PUT, DELETE, etc.
                        .allowedHeaders("*") // Todos los headers
                        .maxAge(3600); // Tiempo de cache para preflight
            }
        };
    }
}
