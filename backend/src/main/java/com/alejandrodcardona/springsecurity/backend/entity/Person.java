package com.alejandrodcardona.springsecurity.backend.entity;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private Date birthDate;

    private Set<Address> addresses;

}
