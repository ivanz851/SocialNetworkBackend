package com.ivanz.socialnetworkbackend.user.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthResponseTest {

    @Test
    void recordStoresTokenAndImplementsEquals() {
        AuthResponse r1 = new AuthResponse("jwt-token");
        AuthResponse r2 = new AuthResponse("jwt-token");

        assertThat(r1.token()).isEqualTo("jwt-token");
        assertThat(r1).isEqualTo(r2)
                .hasSameHashCodeAs(r2);

        assertThat(r1.toString()).contains("jwt-token");
    }
}
