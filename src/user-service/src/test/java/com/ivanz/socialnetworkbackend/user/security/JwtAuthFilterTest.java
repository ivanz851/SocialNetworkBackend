package com.ivanz.socialnetworkbackend.user.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JwtAuthFilterTest {

    @Mock private JwtService jwtService;
    @Mock private FilterChain chain;

    private JwtAuthFilter filter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        filter = new JwtAuthFilter(jwtService);
        SecurityContextHolder.clearContext();
    }

    @Test
    void missingHeader_passesThrough() throws Exception {
        MockHttpServletRequest  req  = new MockHttpServletRequest();
        MockHttpServletResponse res  = new MockHttpServletResponse();

        filter.doFilter(req, res, chain);

        verify(chain).doFilter(req, res);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    void validToken_setsAuthenticationAndContinues() throws Exception {
        MockHttpServletRequest  req = new MockHttpServletRequest();
        req.addHeader("Authorization", "Bearer token");
        MockHttpServletResponse res = new MockHttpServletResponse();

        when(jwtService.extractUserId("Bearer token")).thenReturn(7L);

        filter.doFilter(req, res, chain);

        verify(chain).doFilter(req, res);
        assertThat(SecurityContextHolder.getContext().getAuthentication())
                .isNotNull()
                .extracting("principal").isEqualTo(7L);
    }

    @Test
    void invalidToken_returns403AndStopsChain() throws Exception {
        MockHttpServletRequest  req = new MockHttpServletRequest();
        req.addHeader("Authorization", "Bearer bad");
        MockHttpServletResponse res = new MockHttpServletResponse();

        when(jwtService.extractUserId("Bearer bad")).thenThrow(new RuntimeException("bad-token"));

        filter.doFilter(req, res, chain);

        verify(chain, never()).doFilter(req, res);
        assertThat(res.getStatus()).isEqualTo(HttpServletResponse.SC_FORBIDDEN);
    }
}
