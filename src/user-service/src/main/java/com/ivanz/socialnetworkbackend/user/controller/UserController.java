package com.ivanz.socialnetworkbackend.user.controller;

import com.ivanz.socialnetworkbackend.user.dto.*;
import com.ivanz.socialnetworkbackend.user.security.JwtService;
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
    private final JwtService jwtService;

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

    @GetMapping("/profile")
    public UserDto getProfile(@RequestHeader("Authorization") String authHeader) {
        Long userId = jwtService.extractUserId(authHeader);
        return userService.getProfile(userId);
    }

    @PutMapping("/profile")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProfile(@RequestHeader("Authorization") String authHeader,
                              @RequestBody @Valid ProfileUpdateRequest req) {
        Long userId = jwtService.extractUserId(authHeader);
        userService.updateProfile(userId, req);
    }


}
