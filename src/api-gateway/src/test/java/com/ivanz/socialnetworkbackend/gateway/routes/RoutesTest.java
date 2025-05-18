package com.ivanz.socialnetworkbackend.gateway.routes;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class RoutesTest {

    private final RouterFunction<ServerResponse> router =
            new Routes().userServiceRoute();

    private static ServerRequest buildRequest(String method, String path) {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest(method, path);
        return ServerRequest.create(servletRequest, Collections.emptyList());
    }

    @Test
    void userServiceRoute_matchesPathsThatStartWithApiUser() {
        ServerRequest request = buildRequest("GET", "/api/user/42");

        Optional<HandlerFunction<ServerResponse>> handler = router.route(request);

        assertThat(handler).isPresent();
    }

    @Test
    void userServiceRoute_doesNotMatchOtherPaths() {
        ServerRequest request = buildRequest("GET", "/api/post/42");

        Optional<HandlerFunction<ServerResponse>> handler = router.route(request);

        assertThat(handler).isEmpty();
    }
}
