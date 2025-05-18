package com.ivanz.socialnetworkbackend.user.exception;

import com.ivanz.socialnetworkbackend.user.dto.ApiError;
import jakarta.security.auth.message.AuthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    /* ---------- helper to check @ResponseStatus ---------- */

    private void assertStatus(String methodName, Class<?> param, HttpStatus expected) throws NoSuchMethodException {
        ResponseStatus ann = handler.getClass()
                .getMethod(methodName, param)
                .getAnnotation(ResponseStatus.class);
        assertThat(ann).isNotNull();
        assertThat(ann.value()).isEqualTo(expected);
    }

    /* ---------- AUTH (401) ---------- */

    @Test
    void authError_returnsProperApiErrorAndStatus() throws Exception {
        AuthException ex = new AuthException("bad creds");

        ApiError err = handler.authError(ex);

        assertThat(err.code()).isEqualTo("AUTH_ERROR");
        assertThat(err.message()).isEqualTo("bad creds");

        assertStatus("authError", AuthException.class, HttpStatus.UNAUTHORIZED);
    }

    /* ---------- CONFLICT (409) ---------- */

    @Test
    void conflict_returnsProperApiErrorAndStatus() throws Exception {
        ConflictException ex = new ConflictException("duplicate");

        ApiError err = handler.conflict(ex);

        assertThat(err.code()).isEqualTo("CONFLICT");
        assertThat(err.message()).isEqualTo("duplicate");

        assertStatus("conflict", ConflictException.class, HttpStatus.CONFLICT);
    }

    /* ---------- NOT-FOUND (404) ---------- */

    @Test
    void notFound_returnsProperApiErrorAndStatus() throws Exception {
        ChangeSetPersister.NotFoundException ex =
                new ChangeSetPersister.NotFoundException();

        ApiError err = handler.notFound(ex);

        assertThat(err.code()).isEqualTo("NOT_FOUND");
        assertThat(err.message()).isEqualTo(ex.getMessage());

        assertStatus("notFound", ChangeSetPersister.NotFoundException.class,
                HttpStatus.NOT_FOUND);
    }

    /* ---------- INTERNAL (500) ---------- */

    @Test
    void internalError_returnsProperApiErrorAndStatus() throws Exception {
        Exception ex = new Exception("boom");

        ApiError err = handler.internalError(ex);

        assertThat(err.code()).isEqualTo("INTERNAL_ERROR");
        assertThat(err.message()).isEqualTo("boom");

        assertStatus("internalError", Exception.class,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
