package com.helpy.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("DELETE, GET, OPTIONS, PATCH, POST, PUT")
                        .allowedHeaders("x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN")
                        .allowCredentials(true).maxAge(3600);
            }
        };
    }
}