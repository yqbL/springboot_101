package com.example.springboot_101.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 允许的域名，可以使用 "*" 或者指定具体的域名
        config.addAllowedOriginPattern("*");
        // 是否允许发送 Cookie
        config.setAllowCredentials(true);
        // 允许的 HTTP 方法
        config.addAllowedMethod("*");
        // 允许的请求头
        config.addAllowedHeader("*");

        // 配置路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 对所有路径生效

        return new CorsFilter(source);
    }
}
