package com.alejandrodcardona.springsecurity.backend.service.facebook;

import com.alejandrodcardona.springsecurity.backend.dto.UserDetailsImpl;
import com.alejandrodcardona.springsecurity.backend.dto.facebook.FacebookUser;
import com.alejandrodcardona.springsecurity.backend.entity.Person;
import com.alejandrodcardona.springsecurity.backend.entity.User;
import com.alejandrodcardona.springsecurity.backend.entity.Role;
import com.alejandrodcardona.springsecurity.backend.exception.BadRequestException;
import com.alejandrodcardona.springsecurity.backend.service.JwtService;
import com.alejandrodcardona.springsecurity.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class FacebookService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    private final String API_BASE = "https://graph.facebook.com";

    public FacebookUser get(String accessToken) {
        var path = "/me?fields={fields}&redirect={redirect}&access_token={access_token}";
        var fields = "email,first_name,last_name,id,picture.width(720).height(720)";
        final Map<String, String> variables = new HashMap<>();
        variables.put("fields", fields);
        variables.put("redirect", "false");
        variables.put("access_token", accessToken);
        return restTemplate
                .getForObject(API_BASE + path, FacebookUser.class, variables);
    }

    public String login(String accessToken) {
        var facebookUser = get(accessToken);
        return userService.findById(facebookUser.getId())
                .or(() -> Optional.ofNullable(userService.register(convertTo(facebookUser), Role.FACEBOOK_USER)))
                .map(UserDetailsImpl::new)
                .map(userDetails -> new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()))
                .map(jwtService::generateToken)
                .orElseThrow(() ->
                        new BadRequestException("unable to login facebook user id " + facebookUser.getId()));
    }

    private User convertTo(FacebookUser facebookUser) {
        return User.builder()
                .id(facebookUser.getId())
                .email(facebookUser.getEmail())
                .person(
                        Person.builder()
                                .firstName(facebookUser.getFirstName())
                                .lastName(facebookUser.getLastName())
                                .build()
                )
                .pictureUrl(facebookUser.getPicture().getData().getUrl())
                .build();
    }

}
