package com.ivanz.socialnetworkbackend.content.repository;

import com.ivanz.socialnetworkbackend.content.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
class PostRepositoryTest {

    @Autowired private PostRepository repo;

    @Test
    void save_and_read() {
        Post p = new Post();
        p.setTitle("t");
        p.setContent("c");
        p.setAuthorId(7L);
        p.setTags(List.of("a", "b"));

        Post saved = repo.save(p);
        assertThat(saved.getId()).isNotNull();

        Post fetched = repo.findById(saved.getId()).orElseThrow();
        assertThat(fetched.getTags()).containsExactly("a", "b");
    }
}
