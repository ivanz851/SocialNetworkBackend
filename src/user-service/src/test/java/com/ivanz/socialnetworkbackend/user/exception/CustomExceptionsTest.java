package com.ivanz.socialnetworkbackend.user.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomExceptionsTest {

    @Test
    void authRuntimeException_inheritsRuntimeAndStoresMessage() {
        AuthRuntimeException ex = new AuthRuntimeException("msg");
        assertThat(ex).isInstanceOf(RuntimeException.class);
        assertThat(ex.getMessage()).isEqualTo("msg");
    }

    @Test
    void conflictException_inheritsRuntimeAndStoresMessage() {
        ConflictException ex = new ConflictException("dup");
        assertThat(ex.getMessage()).isEqualTo("dup");
    }

    @Test
    void notFoundException_inheritsRuntimeAndStoresMessage() {
        NotFoundException ex = new NotFoundException("404");
        assertThat(ex.getMessage()).isEqualTo("404");
    }
}
