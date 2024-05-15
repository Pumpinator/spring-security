package com.alejandrodcardona.springsecurity.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponse extends Response {

    private LocalDateTime timestamp;

    public ErrorResponse(Boolean success, LocalDateTime timestamp, int code, String message) {
        super(success, code, message);
        this.timestamp = timestamp;
    }

}
