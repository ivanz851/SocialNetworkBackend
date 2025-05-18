package com.ivanz.socialnetworkbackend.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserDto(
        Long id,
        String login,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String email,
        String phone,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) { }
