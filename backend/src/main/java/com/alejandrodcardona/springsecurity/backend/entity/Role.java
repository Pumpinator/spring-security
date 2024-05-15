package com.alejandrodcardona.springsecurity.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    public static final Role ADMIN = new Role("ADMIN");
    public static final Role USER = new Role("USER");
    public static final Role GUEST = new Role("GUEST");
    public static final Role FACEBOOK_USER = new Role("FACEBOOK_USER");

    private String name;

}
