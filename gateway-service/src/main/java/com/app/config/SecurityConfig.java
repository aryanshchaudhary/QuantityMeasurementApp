package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeExchange(exchange -> exchange

                // Allow preflight
                .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Auth endpoints
                .pathMatchers("/auth/**").permitAll()

                // ✅ PUBLIC APIs (VERY IMPORTANT)
                .pathMatchers(
                    "/api/convert",
                    "/api/add",
                    "/api/subtract",
                    "/api/divide",
                    "/api/compare"
                ).permitAll()

                // 🔒 PROTECTED
                .pathMatchers("/history/**").authenticated()

                .anyExchange().permitAll()
            );

        return http.build();
    }
}