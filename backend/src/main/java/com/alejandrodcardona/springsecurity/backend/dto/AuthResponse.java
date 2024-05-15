package com.alejandrodcardona.springsecurity.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthResponse extends Response {

    private String accessToken;

    public AuthResponse(Boolean success, int code, String accessToken) {
        super(success, code);
        this.accessToken = accessToken;
    }

}
