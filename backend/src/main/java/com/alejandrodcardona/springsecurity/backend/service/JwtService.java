package com.alejandrodcardona.springsecurity.backend.service;

import com.alejandrodcardona.springsecurity.backend.configuration.JwtConfiguration;
import com.alejandrodcardona.springsecurity.backend.exception.TokenException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtService {

    private final JwtConfiguration jwtConfiguration;

    public JwtService(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    public String generateToken(Authentication authentication) {
        Long now = System.currentTimeMillis();

        return Jwts
                .builder()
                .setSubject(authentication.getName())
                .claim(
                        "authorities",
                        authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                )
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfiguration.getExpiration()))
                .signWith(
                        SignatureAlgorithm.HS512,
                        jwtConfiguration.getSecret().getBytes()
                )
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfiguration.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtConfiguration.getSecret().getBytes())
                    .parseClaimsJws(authToken);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException |
                 SignatureException | IllegalArgumentException e) {
            return false;
        }
        return true;
    }

}
