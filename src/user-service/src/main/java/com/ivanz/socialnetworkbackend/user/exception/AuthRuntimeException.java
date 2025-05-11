package com.ivanz.socialnetworkbackend.user.exception;

public class AuthRuntimeException extends RuntimeException {

    public AuthRuntimeException(String message) {
        super(message);
    }

    public AuthRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}