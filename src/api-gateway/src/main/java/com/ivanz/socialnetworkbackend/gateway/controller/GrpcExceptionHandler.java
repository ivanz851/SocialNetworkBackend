package com.ivanz.socialnetworkbackend.gateway.controller;

import org.springframework.http.HttpStatus;
import com.ivanz.socialnetworkbackend.gateway.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GrpcExceptionHandler {

    @ExceptionHandler(io.grpc.StatusRuntimeException.class)
    public ResponseEntity<ErrorResponse> handle(io.grpc.StatusRuntimeException ex) {
        HttpStatus httpStatus = switch (ex.getStatus().getCode()) {
            case NOT_FOUND        -> HttpStatus.NOT_FOUND;
            case INVALID_ARGUMENT -> HttpStatus.BAD_REQUEST;
            case ALREADY_EXISTS   -> HttpStatus.CONFLICT;
            default               -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
        return ResponseEntity
                .status(httpStatus)
                .body(new ErrorResponse(ex.getStatus().getDescription()));
    }
}
