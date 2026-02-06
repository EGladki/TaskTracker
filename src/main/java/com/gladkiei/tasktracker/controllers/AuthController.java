package com.gladkiei.tasktracker.controllers;

import com.gladkiei.tasktracker.dtos.AuthRequestDto;
import com.gladkiei.tasktracker.dtos.user.UserResponseDto;
import com.gladkiei.tasktracker.auth.jwt.JwtAuthResponse;
import com.gladkiei.tasktracker.services.AuthService;
import com.gladkiei.tasktracker.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/user")
    public JwtAuthResponse registration(@Valid @RequestBody AuthRequestDto request) {
        return authService.signUp(request);
    }

    @PostMapping("/login")
    public JwtAuthResponse login(@Valid @RequestBody AuthRequestDto request) {
        return authService.signIn(request);
    }

    @GetMapping("/user")
    public UserResponseDto getCurrentUser() {
        return userService.getCurrentUser();
    }
}
