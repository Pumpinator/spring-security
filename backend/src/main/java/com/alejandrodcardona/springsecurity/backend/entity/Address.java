package com.alejandrodcardona.springsecurity.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Address {

    @Id
    private String id;

    private String street;

    private String city;

    private String state;

    private String zipCode;

    private String country;

    private String phoneNumber;

}
