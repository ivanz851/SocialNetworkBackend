package com.ivanz.socialnetworkbackend.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ProfileUpdateRequest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Past
        LocalDate birthDate,
        @Email
        String email,
        @Pattern(regexp="^\\+?[0-9]{10,15}$")
        String phone) { }
