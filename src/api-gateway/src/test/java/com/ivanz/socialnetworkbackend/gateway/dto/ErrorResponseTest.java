package com.ivanz.socialnetworkbackend.gateway.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorResponseTest {

    @Test
    void recordStoresMessageAndHasProperEquals() {
        ErrorResponse err1 = new ErrorResponse("boom");
        ErrorResponse err2 = new ErrorResponse("boom");

        assertThat(err1.message()).isEqualTo("boom");

        assertThat(err1).isEqualTo(err2)
                .hasSameHashCodeAs(err2);

        assertThat(err1.toString()).contains("boom");
    }
}
