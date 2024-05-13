package com.alejandrodcardona.springsecurity.backend.dto;

import com.mongodb.lang.NonNull;
import lombok.Data;

@Data
public class SignUpRequest {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    @NonNull
    private String password;
}
