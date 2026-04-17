package com.app.authservice.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.authservice.entity.User;
import com.app.authservice.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtUtil jwtUtil,
                                   UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // ✅ 1. SKIP AUTH ENDPOINTS (VERY IMPORTANT)
        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ 2. GET AUTH HEADER
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // ✅ 3. EXTRACT TOKEN
            String token = authHeader.substring(7);

            // ✅ 4. VALIDATE TOKEN
            if (jwtUtil.validateToken(token)) {

                String email = jwtUtil.extractEmail(token);

                // ✅ 5. SET AUTHENTICATION ONLY IF NOT SET
                if (SecurityContextHolder.getContext().getAuthentication() == null) {

                    User user = userRepository.findByEmail(email).orElse(null);

                    if (user != null) {

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        email,
                                        null,
                                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
                                );

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }

        } catch (Exception e) {
            // ✅ 6. HANDLE INVALID TOKEN SAFELY
            System.out.println("JWT ERROR: " + e.getMessage());
        }

        // ✅ 7. CONTINUE FILTER CHAIN
        filterChain.doFilter(request, response);
    }
}