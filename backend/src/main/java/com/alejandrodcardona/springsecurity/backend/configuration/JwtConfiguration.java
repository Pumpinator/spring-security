package com.alejandrodcardona.springsecurity.backend.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
