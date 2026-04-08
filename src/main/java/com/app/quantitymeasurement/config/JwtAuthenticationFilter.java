package com.app.quantitymeasurement.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.quantitymeasurement.user.User;
import com.app.quantitymeasurement.user.UserRepository;

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

        System.out.println("==== JWT FILTER START ====");

        String authHeader = request.getHeader("Authorization");
        System.out.println("HEADER: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);
            System.out.println("TOKEN: " + token);

            boolean isValid = jwtUtil.validateToken(token);
            System.out.println("VALID TOKEN: " + isValid);

            if (isValid) {

                String email = jwtUtil.extractEmail(token);
                System.out.println("EMAIL: " + email);

                // ✅ VERY IMPORTANT CHECK
                if (SecurityContextHolder.getContext().getAuthentication() == null) {

                    User user = userRepository.findByEmail(email).orElse(null);
                    System.out.println("USER FOUND: " + user);

                    if (user != null) {

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        email,
                                        null,
                                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
                                );

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println("AUTH SET SUCCESS ✅");
                    } else {
                        System.out.println("USER NOT FOUND ❌");
                    }
                }

            } else {
                System.out.println("INVALID TOKEN ❌");
            }
        } else {
            System.out.println("NO TOKEN FOUND ❌");
        }

        System.out.println("==== JWT FILTER END ====");

        filterChain.doFilter(request, response);
    }
}