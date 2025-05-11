package com.ivanz.socialnetworkbackend.user.controller;

import com.ivanz.socialnetworkbackend.user.dto.AuthResponse;
import com.ivanz.socialnetworkbackend.user.dto.UserDto;
import com.ivanz.socialnetworkbackend.user.dto.UserRegisterRequest;
import com.ivanz.socialnetworkbackend.user.dto.UserLoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.ivanz.socialnetworkbackend.user.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody @Valid UserRegisterRequest req) {
        userService.register(req);
        return "User registered";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid UserLoginRequest req) {
        return userService.authenticate(req);
    }

}
