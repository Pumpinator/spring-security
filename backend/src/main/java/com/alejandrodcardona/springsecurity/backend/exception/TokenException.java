package com.alejandrodcardona.springsecurity.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenException extends RuntimeException {

        public TokenException(String message) {
            super(message);
        }

        public TokenException() {
        }
}
