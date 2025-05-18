package com.ivanz.socialnetworkbackend.user.dto;

public record UserLoginRequest(
        String loginOrEmail,
        String password) {}