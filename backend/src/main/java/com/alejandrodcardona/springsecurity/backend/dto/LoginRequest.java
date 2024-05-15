package com.alejandrodcardona.springsecurity.backend.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequest {

    @NonNull
    private String email;

    @NonNull
    private String password;

}
