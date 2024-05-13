package com.alejandrodcardona.springsecurity.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BackendResponse {

    private Boolean success;
    private String message;

}
