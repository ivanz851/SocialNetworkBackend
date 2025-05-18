package com.ivanz.socialnetworkbackend.user.security;

import com.ivanz.socialnetworkbackend.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class JwtServiceTest {

    private JwtService service;
    private final String secret = "01234567890123456789012345678901";

    @BeforeEach
    void setUp() {
        service = new JwtService();
        ReflectionTestUtils.setField(service, "secret", secret);
    }

    @Test
    void generateAndExtract_roundTripOk() {
        User u = new User();
        u.setId(42L);
        u.setLogin("john");

        String token = service.generateToken(u);
        assertThat(token).isNotBlank();

        Long extracted = service.extractUserId("Bearer " + token);
        assertThat(extracted).isEqualTo(42L);
    }
}
