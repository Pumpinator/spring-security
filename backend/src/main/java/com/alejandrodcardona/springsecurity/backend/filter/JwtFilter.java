package com.alejandrodcardona.springsecurity.backend.filter;

import com.alejandrodcardona.springsecurity.backend.configuration.JwtConfiguration;
import com.alejandrodcardona.springsecurity.backend.entity.User;
import com.alejandrodcardona.springsecurity.backend.entity.UserDetailsImpl;
import com.alejandrodcardona.springsecurity.backend.service.JwtService;
import com.alejandrodcardona.springsecurity.backend.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtConfiguration jwtConfiguration;
    private JwtService jwtService;
    private UserService userService;

    public JwtFilter(JwtConfiguration jwtConfiguration, JwtService jwtService, UserService userService) {
        this.jwtConfiguration = jwtConfiguration;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(jwtConfiguration.getHeader());

        if (header == null || !header.startsWith(jwtConfiguration.getPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace(jwtConfiguration.getPrefix(), "");

        if (jwtService.validateToken(token)) {
            Claims claims = jwtService.getClaims(token);
            String email = claims.getSubject();
            UsernamePasswordAuthenticationToken auth = userService.findByEmail(email)
                    .map(UserDetailsImpl::new)
                    .map(userDetails -> {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        return authentication;
                    }).orElse(null);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
