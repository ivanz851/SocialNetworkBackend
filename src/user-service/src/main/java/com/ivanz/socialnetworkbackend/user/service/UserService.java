package com.ivanz.socialnetworkbackend.user.service;

import com.ivanz.socialnetworkbackend.user.dto.*;
import com.ivanz.socialnetworkbackend.user.exception.ConflictException;
import com.ivanz.socialnetworkbackend.user.exception.NotFoundException;
import com.ivanz.socialnetworkbackend.user.exception.AuthRuntimeException;
import com.ivanz.socialnetworkbackend.user.model.User;
import com.ivanz.socialnetworkbackend.user.repository.UserRepository;
import com.ivanz.socialnetworkbackend.user.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public void register(UserRegisterRequest req) {
        if (userRepo.existsByLogin(req.login()))
            throw new ConflictException("Login already taken");
        if (userRepo.existsByEmail(req.email()))
            throw new ConflictException("Email already taken");

        User user = new User();
        user.setLogin(req.login());
        user.setEmail(req.email());
        user.setPassword(passwordEncoder.encode(req.password()));

        userRepo.save(user);
        log.info("User {} registered", user.getLogin());
    }

    @Transactional(readOnly = true)
    public AuthResponse authenticate(UserLoginRequest req) {

        User user = userRepo
                .findByLoginOrEmail(req.loginOrEmail(), req.loginOrEmail())
                .orElseThrow(() -> new AuthRuntimeException("User not found"));

        if (!passwordEncoder.matches(req.password(), user.getPassword()))
            throw new AuthRuntimeException("Wrong password");

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    @Transactional
    public void updateProfile(Long userId, ProfileUpdateRequest p) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (p.firstName() != null) user.setFirstName(p.firstName());
        if (p.lastName() != null) user.setLastName(p.lastName());
        if (p.birthDate() != null) user.setBirthDate(p.birthDate());
        if (p.phone() != null) user.setPhone(p.phone());

        log.info("User {} updated profile", user.getLogin());
    }
}
