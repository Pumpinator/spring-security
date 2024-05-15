package com.alejandrodcardona.springsecurity.backend.controller;

import com.alejandrodcardona.springsecurity.backend.dto.AuthResponse;
import com.alejandrodcardona.springsecurity.backend.dto.Response;
import com.alejandrodcardona.springsecurity.backend.dto.LoginRequest;
import com.alejandrodcardona.springsecurity.backend.dto.SignUpRequest;
import com.alejandrodcardona.springsecurity.backend.entity.Person;
import com.alejandrodcardona.springsecurity.backend.entity.Role;
import com.alejandrodcardona.springsecurity.backend.entity.User;
import com.alejandrodcardona.springsecurity.backend.exception.UserException;
import com.alejandrodcardona.springsecurity.backend.service.UserService;
import com.alejandrodcardona.springsecurity.backend.exception.BadRequestException;
import com.alejandrodcardona.springsecurity.backend.service.facebook.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private FacebookService facebookService;

    @PostMapping(value = "/api/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = userService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(true, HttpStatus.OK.value(), token));
    }

    @PostMapping(value = "/api/facebook/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> facebookLogin(@RequestBody Map<String, String> request) {
        String token = facebookService.login(request.get("accessToken"));
        return ResponseEntity.ok(new AuthResponse(true, HttpStatus.OK.value(), token));
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

        return ResponseEntity.created(location).body(new Response(true, HttpStatus.CREATED.value(), "User registered successfully"));
    }

}
