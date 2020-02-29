package com.dev.cinema.controller;

import com.dev.cinema.model.dto.request.UserRegistrationDto;
import com.dev.cinema.model.dto.request.UserRequestDto;
import com.dev.cinema.service.AuthenticationService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String login(@RequestBody @ Valid UserRequestDto userRequestDto) {
        authenticationService.login(userRequestDto.getEmail(), userRequestDto.getPassword());
        return "Logged in successfully.";
    }

    @PostMapping("/registration")
    public String register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        authenticationService.register(userRegistrationDto.getEmail(),
                userRegistrationDto.getPassword());
        return "Registration completed.";
    }
}
