package com.ivanz.socialnetworkbackend.user.service;

import com.ivanz.socialnetworkbackend.user.dto.*;
import com.ivanz.socialnetworkbackend.user.exception.AuthRuntimeException;
import com.ivanz.socialnetworkbackend.user.exception.ConflictException;
import com.ivanz.socialnetworkbackend.user.exception.NotFoundException;
import com.ivanz.socialnetworkbackend.user.model.User;
import com.ivanz.socialnetworkbackend.user.repository.UserRepository;
import com.ivanz.socialnetworkbackend.user.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock  private UserRepository userRepo;
    @Mock  private PasswordEncoder passwordEncoder;
    @Mock  private JwtService jwtService;

    private UserService service;

    @BeforeEach
    void init() {
        service = new UserService(userRepo, passwordEncoder, jwtService);
    }


    private static User sampleUser() {
        User u = new User();
        u.setId(1L);
        u.setLogin("john");
        u.setEmail("john@mail.com");
        u.setPassword("hashed");
        u.setFirstName("John");
        u.setLastName("Doe");
        u.setBirthDate(LocalDate.of(1990, 1, 1));
        u.setPhone("+12345678901");
        u.setCreatedAt(LocalDateTime.now());
        u.setUpdatedAt(LocalDateTime.now());
        return u;
    }


    @Test
    void register_savesEncodedPassword_andLogs() {
        UserRegisterRequest req = new UserRegisterRequest("john", "john@mail.com", "secret");
        when(userRepo.existsByLogin("john")).thenReturn(false);
        when(userRepo.existsByEmail("john@mail.com")).thenReturn(false);
        when(passwordEncoder.encode("secret")).thenReturn("hashed");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        when(userRepo.save(captor.capture())).thenAnswer(a -> a.getArgument(0));

        service.register(req);

        User saved = captor.getValue();
        assertThat(saved.getLogin()).isEqualTo("john");
        assertThat(saved.getEmail()).isEqualTo("john@mail.com");
        assertThat(saved.getPassword()).isEqualTo("hashed");
    }

    @Test
    void register_throwsConflict_whenLoginTaken() {
        when(userRepo.existsByLogin("john")).thenReturn(true);

        assertThatThrownBy(() ->
                service.register(new UserRegisterRequest("john", "a@mail.com", "pass")))
                .isInstanceOf(ConflictException.class);
        verify(userRepo, never()).save(any());
    }

    @Test
    void register_throwsConflict_whenEmailTaken() {
        when(userRepo.existsByLogin("john")).thenReturn(false);
        when(userRepo.existsByEmail("john@mail.com")).thenReturn(true);

        assertThatThrownBy(() ->
                service.register(new UserRegisterRequest("john", "john@mail.com", "pass")))
                .isInstanceOf(ConflictException.class);
    }


    @Test
    void authenticate_success_returnsJwt() {
        User user = sampleUser();
        UserLoginRequest req = new UserLoginRequest("john", "secret");

        when(userRepo.findByLoginOrEmail("john", "john")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("secret", user.getPassword())).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("jwt");

        AuthResponse resp = service.authenticate(req);

        assertThat(resp.token()).isEqualTo("jwt");
    }

    @Test
    void authenticate_wrongPassword_throws() {
        User user = sampleUser();
        when(userRepo.findByLoginOrEmail(any(), any())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        assertThatThrownBy(() ->
                service.authenticate(new UserLoginRequest("john", "bad")))
                .isInstanceOf(AuthRuntimeException.class);
    }

    @Test
    void authenticate_userNotFound_throws() {
        when(userRepo.findByLoginOrEmail(any(), any())).thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.authenticate(new UserLoginRequest("john", "pass")))
                .isInstanceOf(AuthRuntimeException.class);
    }


    @Test
    void getProfile_returnsMappedDto() {
        User u = sampleUser();
        when(userRepo.findById(1L)).thenReturn(Optional.of(u));

        UserDto dto = service.getProfile(1L);

        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.email()).isEqualTo("john@mail.com");
        assertThat(dto.firstName()).isEqualTo("John");
    }

    @Test
    void getProfile_userNotFound_throws() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getProfile(1L))
                .isInstanceOf(NotFoundException.class);
    }


    @Test
    void updateProfile_updatesOnlyProvidedFields() {
        User u = sampleUser();
        when(userRepo.findById(1L)).thenReturn(Optional.of(u));

        ProfileUpdateRequest upd = new ProfileUpdateRequest(
                "New", null, null, null, "+98765432100");

        service.updateProfile(1L, upd);

        assertThat(u.getFirstName()).isEqualTo("New");
        assertThat(u.getLastName()).isEqualTo("Doe");
        assertThat(u.getPhone()).isEqualTo("+98765432100");
    }

    @Test
    void updateProfile_userNotFound_throws() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        ProfileUpdateRequest upd = new ProfileUpdateRequest("A", "B",
                LocalDate.of(1990,1,1), "a@b", "+1");

        assertThatThrownBy(() -> service.updateProfile(1L, upd))
                .isInstanceOf(NotFoundException.class);
    }
}
