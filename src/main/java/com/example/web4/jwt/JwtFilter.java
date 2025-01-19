package com.example.web4.jwt;

import com.example.web4.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
public class JwtFilter extends GenericFilter {
    private JwtUtil jwtUtil;
    private UserService service;

    public JwtFilter(JwtUtil jwtUtil, UserService service) {
        this.jwtUtil = jwtUtil;
        this.service = service;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            String token = getTokenFromRequest((HttpServletRequest) servletRequest);
            if ((token != null) && (jwtUtil.validateToken(token))) {
                String name = String.valueOf(jwtUtil.validateToken(token));
                var user = this.service.getByLogin(name);
                if (user != null) {
                    SecurityContextHolder.getContext().setAuthentication((Authentication) user);
                }
            }

            filterChain.doFilter(servletRequest, servletResponse);
        }


    }
