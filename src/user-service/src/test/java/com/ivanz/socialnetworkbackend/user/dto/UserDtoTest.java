package com.ivanz.socialnetworkbackend.user.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class UserDtoTest {

    @Test
    void recordStoresAllFieldsAndSupportsEquality() {
        LocalDate birth = LocalDate.of(1990, 5, 5);
        LocalDateTime now = LocalDateTime.now();

        UserDto dto1 = new UserDto(1L, "login", "John", "Doe",
                birth, "a@b", "+123", now, now);
        UserDto dto2 = new UserDto(1L, "login", "John", "Doe",
                birth, "a@b", "+123", now, now);

        assertThat(dto1).isEqualTo(dto2)
                .hasSameHashCodeAs(dto2);
        assertThat(dto1.email()).isEqualTo("a@b");
    }
}
