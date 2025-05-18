package com.ivanz.socialnetworkbackend.user.repository;

import com.ivanz.socialnetworkbackend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);

    Optional<User> findByLoginOrEmail(String login, String email);
}
