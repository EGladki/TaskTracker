package com.gladkiei.tasktracker.controllers;

import com.gladkiei.tasktracker.dtos.AuthRequestDto;
import com.gladkiei.tasktracker.jwt.auth.JwtAuthResponse;
import com.gladkiei.tasktracker.services.AuthService;
import com.gladkiei.tasktracker.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public JwtAuthResponse registration(@RequestBody AuthRequestDto request) {
        return authService.signUp(request);
        }

    @PostMapping("/sign-in")
    public JwtAuthResponse login(@RequestBody AuthRequestDto request) {
        return authService.signIn(request);
    }
}
