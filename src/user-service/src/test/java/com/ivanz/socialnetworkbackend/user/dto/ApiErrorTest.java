package com.ivanz.socialnetworkbackend.user.dto;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

class ApiErrorTest {

    @Test
    void twoArgConstructor_setsTimestampToNow() {
        Instant before = Instant.now();
        ApiError error = new ApiError("404", "Not found");
        Instant after  = Instant.now();

        assertThat(error.code()).isEqualTo("404");
        assertThat(error.message()).isEqualTo("Not found");
        assertThat(error.timestamp())
                .isBetween(before.minus(1, SECONDS), after.plus(1, SECONDS));
    }

    @Test
    void threeArgConstructor_usesSuppliedTimestamp() {
        Instant ts = Instant.parse("2025-05-18T12:00:00Z");
        ApiError err = new ApiError("500", "Oops", ts);

        assertThat(err.timestamp()).isEqualTo(ts);
    }
}
