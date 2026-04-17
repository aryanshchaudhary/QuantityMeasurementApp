package com.app.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF (for APIs)
            .csrf(csrf -> csrf.disable())

            //  Authorization rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
                .requestMatchers("/auth/**").permitAll()               
                .requestMatchers("/api/**").authenticated()            
                .anyRequest().permitAll()
            )

            // Return 401 instead of redirect
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((req, res, e) -> {
                    res.sendError(401, "Unauthorized");
                })
            )

            // Add JWT filter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

            // Allow H2 console
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}
