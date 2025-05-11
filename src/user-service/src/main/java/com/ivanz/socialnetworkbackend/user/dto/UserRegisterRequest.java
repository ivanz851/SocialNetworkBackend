package com.ivanz.socialnetworkbackend.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(
        @NotBlank String login,
        @Email String email,
        @Size(min = 6) String password) {}
