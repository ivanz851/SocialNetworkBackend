package com.ivanz.socialnetworkbackend.user.repository;

import com.ivanz.socialnetworkbackend.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.flyway.enabled=false"
})
class UserRepositoryTest {

    @Autowired private UserRepository repo;

    private User saveUser(String login, String email) {
        User u = new User();
        u.setLogin(login);
        u.setEmail(email);
        u.setPassword("pwd");
        return repo.save(u);
    }

    @Test
    void existsByLoginAndEmail() {
        saveUser("john", "j@e.com");

        assertThat(repo.existsByLogin("john")).isTrue();
        assertThat(repo.existsByEmail("j@e.com")).isTrue();
    }

    @Test
    void findByLoginOrEmail() {
        User u = saveUser("john", "j@e.com");

        assertThat(repo.findByLogin("john")).contains(u);
        assertThat(repo.findByEmail("j@e.com")).contains(u);
        assertThat(repo.findByLoginOrEmail("john", "john")).contains(u);
        assertThat(repo.findByLoginOrEmail("j@e.com", "j@e.com")).contains(u);
    }
}
