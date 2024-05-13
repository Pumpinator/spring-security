package com.alejandrodcardona.springsecurity.backend.controller;

import com.alejandrodcardona.springsecurity.backend.dto.BackendResponse;
import com.alejandrodcardona.springsecurity.backend.dto.LoginRequest;
import com.alejandrodcardona.springsecurity.backend.dto.SignUpRequest;
import com.alejandrodcardona.springsecurity.backend.entity.Person;
import com.alejandrodcardona.springsecurity.backend.entity.Role;
import com.alejandrodcardona.springsecurity.backend.entity.User;
import com.alejandrodcardona.springsecurity.backend.exception.UserException;
import com.alejandrodcardona.springsecurity.backend.service.UserService;
import com.alejandrodcardona.springsecurity.backend.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/api/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = userService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new BackendResponse(true, token));
    }

    @PostMapping(value = "/api/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody SignUpRequest request) {
        Person person = Person.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .person(person)
                .build();

        try {
            userService.register(user, Role.USER);
        } catch (UserException e) {
            throw new BadRequestException(e.getMessage());
        }

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/{email}")
                .buildAndExpand(user.getEmail()).toUri();

        return ResponseEntity.created(location).body(new BackendResponse(true, "User registered successfully"));
    }

}
