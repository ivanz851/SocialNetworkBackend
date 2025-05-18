package com.ivanz.socialnetworkbackend.content.service;

import com.ivanz.socialnetworkbackend.content.model.Post;
import com.ivanz.socialnetworkbackend.content.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepo;

    public Post createPost(Post post) {
        if (post.getTags() == null) {
            post.setTags(new ArrayList<>());
        }
        return postRepo.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        Post currentPost = postRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

        currentPost.setTitle(updatedPost.getTitle());
        currentPost.setContent(updatedPost.getContent());
        currentPost.setPrivate(updatedPost.isPrivate());
        currentPost.setTags(updatedPost.getTags());

        Post saved = postRepo.save(currentPost);
        log.info("Post {} updated", saved.getId());
        return saved;
    }

    public void deletePost(Long id) {
        if (!postRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
        postRepo.deleteById(id);
        log.info("Post {} deleted", id);
    }

    public Post getPostById(Long id) {
        return postRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
    }

    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepo.findAll(pageable);
    }

}
