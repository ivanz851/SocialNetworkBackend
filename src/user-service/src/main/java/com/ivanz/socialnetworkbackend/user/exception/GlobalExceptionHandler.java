package com.ivanz.socialnetworkbackend.user.exception;

import com.ivanz.socialnetworkbackend.user.dto.ApiError;
import jakarta.security.auth.message.AuthException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* ---------- 401 Unauthorized ---------- */

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError authError(AuthException ex) {
        return new ApiError("AUTH_ERROR", ex.getMessage());
    }

    /* ---------- 409 Conflict ---------- */

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError conflict(ConflictException ex) {
        return new ApiError("CONFLICT", ex.getMessage());
    }

    /* ---------- 404 Not Found ---------- */

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError notFound(ChangeSetPersister.NotFoundException ex) {
        return new ApiError("NOT_FOUND", ex.getMessage());
    }

    /* ---------- 500 Internal Server Error (fallback) ---------- */

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError internalError(Exception ex) {
        return new ApiError("INTERNAL_ERROR", ex.getMessage());
    }
}