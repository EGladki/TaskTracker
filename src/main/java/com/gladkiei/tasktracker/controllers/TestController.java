package com.gladkiei.tasktracker.controllers;

import com.gladkiei.tasktracker.dtos.user.UserResponseDto;
import com.gladkiei.tasktracker.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;

    @GetMapping("/user")
    public UserResponseDto getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
