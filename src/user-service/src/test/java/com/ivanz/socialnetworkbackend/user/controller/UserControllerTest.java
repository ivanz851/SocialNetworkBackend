package com.ivanz.socialnetworkbackend.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ivanz.socialnetworkbackend.user.dto.*;
import com.ivanz.socialnetworkbackend.user.security.JwtService;
import com.ivanz.socialnetworkbackend.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock  private UserService userService;
    @Mock  private JwtService  jwtService;

    private MockMvc      mvc;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new UserController(userService, jwtService))
                .build();
        mapper = new ObjectMapper()
                 .registerModule(new JavaTimeModule());
    }


    @Test
    void register_returns201AndCallsService() throws Exception {
        UserRegisterRequest req = new UserRegisterRequest("john", "john@mail.com", "secret123");

        mvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(content().string("User registered"));

        verify(userService).register(req);
    }


    @Test
    void login_returnsAuthResponseFromService() throws Exception {
        UserLoginRequest req = new UserLoginRequest("john@mail.com", "secret123");
        AuthResponse expected = new AuthResponse("jwt-token");

        when(userService.authenticate(req)).thenReturn(expected);

        String json = mvc.perform(post("/api/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(mapper.readValue(json, AuthResponse.class)).isEqualTo(expected);
    }


    @Test
    void getProfile_extractsUserIdFromJwtAndReturnsDto() throws Exception {
        long id = 123L;
        String token = "Bearer abc";
        UserDto dto = new UserDto(id, "john",
                "John", "Doe",
                LocalDate.of(1990, 1, 1),
                "john@mail.com", "+12345678901",
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now());

        when(jwtService.extractUserId(token)).thenReturn(id);
        when(userService.getProfile(id)).thenReturn(dto);

        String json = mvc.perform(get("/api/user/profile").header("Authorization", token))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(mapper.readValue(json, UserDto.class)).isEqualTo(dto);
    }


    @Test
    void updateProfile_callsServiceAndReturns204() throws Exception {
        long id = 123L;
        String token = "Bearer abc";
        ProfileUpdateRequest body =
                new ProfileUpdateRequest("John", "Doe",
                        LocalDate.of(1990, 1, 1),
                        "john@mail.com", "+12345678901");

        when(jwtService.extractUserId(token)).thenReturn(id);

        mvc.perform(put("/api/user/profile")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().isNoContent());

        verify(userService).updateProfile(id, body);
    }
}
