package com.ivanz.socialnetworkbackend.gateway.controller;

import com.ivanz.socialnetworkbackend.gateway.dto.ErrorResponse;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GrpcExceptionHandlerTest {

    private final GrpcExceptionHandler handler = new GrpcExceptionHandler();

    private static Stream<Object[]> statusMapping() {
        return Stream.of(
                new Object[]{Status.NOT_FOUND,        HttpStatus.NOT_FOUND},
                new Object[]{Status.INVALID_ARGUMENT, HttpStatus.BAD_REQUEST},
                new Object[]{Status.ALREADY_EXISTS,   HttpStatus.CONFLICT},
                new Object[]{Status.UNAUTHENTICATED,  HttpStatus.INTERNAL_SERVER_ERROR}
        );
    }

    @ParameterizedTest
    @MethodSource("statusMapping")
    void handle_mapsGrpcStatusToHttpStatus(Status grpcStatus, HttpStatus expectedHttp) {
        String message = "any-message";
        StatusRuntimeException ex = grpcStatus.withDescription(message).asRuntimeException();

        ResponseEntity<ErrorResponse> response = handler.handle(ex);

        assertThat(response.getStatusCode()).isEqualTo(expectedHttp);
        assertThat(response.getBody()).isNotNull()
                .extracting(ErrorResponse::message)
                .isEqualTo(message);
    }
}
