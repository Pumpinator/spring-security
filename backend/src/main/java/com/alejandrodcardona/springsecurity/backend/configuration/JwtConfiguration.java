package com.alejandrodcardona.springsecurity.backend.configuration;

import com.alejandrodcardona.springsecurity.backend.dto.ErrorResponse;
import com.alejandrodcardona.springsecurity.backend.dto.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Component
public class JwtConfiguration {

    @Value("${spring.security.jwt.uri:}")
    private String uri;

    @Value("${spring.security.jwt.header:}")
    private String header;

    @Value("${spring.security.jwt.prefix:}")
    private String prefix;

    @Value("${spring.security.jwt.expiration:}")
    private int expiration;

    @Value("${spring.security.jwt.secret:}")
    private String secret;

}
