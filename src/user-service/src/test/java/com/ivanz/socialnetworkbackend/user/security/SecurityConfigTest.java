package com.ivanz.socialnetworkbackend.user.security;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;


class SecurityConfigTest {

    private final SecurityConfig config =
            new SecurityConfig(Mockito.mock(JwtAuthFilter.class));

    @Test
    void passwordEncoder_encodesAndMatches() {
        PasswordEncoder enc = config.passwordEncoder();

        String hash = enc.encode("raw");
        assertThat(enc.matches("raw", hash)).isTrue();
    }
}
