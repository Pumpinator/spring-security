package com.alejandrodcardona.springsecurity.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Response {

    private Boolean success;
    private int code;
    private String message;

    public Response(Boolean success, int code) {
        this.success = success;
        this.code = code;
    }
}

