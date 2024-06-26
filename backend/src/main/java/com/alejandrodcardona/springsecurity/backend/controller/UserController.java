package com.alejandrodcardona.springsecurity.backend.controller;

import com.alejandrodcardona.springsecurity.backend.entity.Person;
import com.alejandrodcardona.springsecurity.backend.entity.User;
import com.alejandrodcardona.springsecurity.backend.dto.UserDetailsImpl;
import com.alejandrodcardona.springsecurity.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/api/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(
                User.builder()
                        .id(userDetails.getUser().getId())
                        .email(userDetails.getUser().getEmail())
                        .password(userDetails.getUser().getPassword())
                        .createdAt(userDetails.getUser().getCreatedAt())
                        .updatedAt(userDetails.getUser().getUpdatedAt())
                        .enabled(userDetails.getUser().isEnabled())
                        .person(
                                Person.builder()
                                        .firstName(userDetails.getUser().getPerson().getFirstName())
                                        .lastName(userDetails.getUser().getPerson().getLastName())
                                        .birthDate(userDetails.getUser().getPerson().getBirthDate())
                                        .addresses(userDetails.getUser().getPerson().getAddresses())
                                        .build()
                        )
                        .roles(userDetails.getUser().getRoles())
                        .build()
        );
    }

    @GetMapping(value = "/api/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable String id) {
        User user = userService.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/api/user/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(users);
    }

}
