package com.ivanz.socialnetworkbackend.content.service;

import com.ivanz.socialnetworkbackend.content.model.Post;
import com.ivanz.socialnetworkbackend.content.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock  private PostRepository repo;
    private PostService          service;

    @BeforeEach
    void setUp() { service = new PostService(repo); }

    private static Post sample() {
        Post p = new Post();
        p.setId(1L);
        p.setTitle("t");
        p.setContent("c");
        p.setAuthorId(7L);
        p.setTags(List.of("tag"));
        return p;
    }

    /* ---------- createPost ---------- */

    @Test
    void createPost_whenTagsNull_setsEmptyList() {
        Post p = new Post();
        p.setTitle("t");
        when(repo.save(any(Post.class))).thenAnswer(a -> a.getArgument(0));

        Post saved = service.createPost(p);

        assertThat(saved.getTags()).isNotNull();
    }

    /* ---------- updatePost ---------- */

    @Test
    void updatePost_success() {
        Post current = sample();
        when(repo.findById(1L)).thenReturn(Optional.of(current));
        when(repo.save(any(Post.class))).thenAnswer(a -> a.getArgument(0));

        Post update = new Post();
        update.setTitle("new");
        update.setPrivate(true);

        Post result = service.updatePost(1L, update);

        assertThat(result.getTitle()).isEqualTo("new");
        assertThat(result.isPrivate()).isTrue();
    }

    @Test
    void updatePost_notFound_throws404() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.updatePost(1L, new Post()))
                .isInstanceOf(ResponseStatusException.class)
                .extracting("statusCode").isEqualTo(HttpStatus.NOT_FOUND);
    }

    /* ---------- deletePost ---------- */

    @Test
    void deletePost_whenMissing_throws404() {
        when(repo.existsById(5L)).thenReturn(false);

        assertThatThrownBy(() -> service.deletePost(5L))
                .isInstanceOf(ResponseStatusException.class)
                .extracting("statusCode").isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deletePost_ok_callsRepo() {
        when(repo.existsById(1L)).thenReturn(true);
        service.deletePost(1L);
        verify(repo).deleteById(1L);
    }

    /* ---------- getPostById ---------- */

    @Test
    void getPostById_notFound_throws404() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getPostById(1L))
                .isInstanceOf(ResponseStatusException.class)
                .extracting("statusCode").isEqualTo(HttpStatus.NOT_FOUND);
    }

    /* ---------- getAllPosts ---------- */

    @Test
    void getAllPosts_delegatesToRepo() {
        Page<Post> page = new PageImpl<>(List.of(sample()));
        Pageable pageable = PageRequest.of(0, 10);
        when(repo.findAll(pageable)).thenReturn(page);

        assertThat(service.getAllPosts(pageable)).isSameAs(page);
    }
}
