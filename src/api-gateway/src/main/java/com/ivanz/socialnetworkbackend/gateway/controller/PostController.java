package com.ivanz.socialnetworkbackend.gateway.controller;

import com.ivanz.socialnetworkbackend.content.grpc.CreatePostRequest;
import com.ivanz.socialnetworkbackend.content.grpc.GetPostRequest;
import com.ivanz.socialnetworkbackend.content.grpc.PaginatedRequest;
import com.ivanz.socialnetworkbackend.content.grpc.PaginatedPostResponse;
import com.ivanz.socialnetworkbackend.content.grpc.PostResponse;
import com.ivanz.socialnetworkbackend.content.grpc.PostServiceGrpc;
import com.ivanz.socialnetworkbackend.content.grpc.UpdatePostRequest;
import com.ivanz.socialnetworkbackend.gateway.dto.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    @GrpcClient("content-service")
    private PostServiceGrpc.PostServiceBlockingStub postStub;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDto createPost(@RequestBody PostCreateDto dto) {
        CreatePostRequest req = CreatePostRequest.newBuilder()
                .setTitle(dto.getTitle())
                .setContent(dto.getContent())
                .setAuthorId(dto.getAuthorId())
                .setIsPrivate(dto.isPrivate())
                .addAllTags(dto.getTags() != null ? dto.getTags() : Collections.emptyList())
                .build();

        PostResponse grpc = postStub.createPost(req);
        return map(grpc);
    }

    @GetMapping("/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        GetPostRequest req = GetPostRequest.newBuilder()
                .setId(id)
                .build();
        PostResponse grpc = postStub.getPostById(req);
        return map(grpc);
    }

    @GetMapping
    public PageResponse<PostResponseDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PaginatedRequest req = PaginatedRequest.newBuilder()
                .setPage(page)
                .setSize(size)
                .build();
        PaginatedPostResponse grpc = postStub.getAllPosts(req);

        var list = grpc.getPostsList().stream()
                .map(this::map)
                .collect(Collectors.toList());

        return new PageResponse<>(list, grpc.getTotalElements(), grpc.getTotalPages());
    }

    @PutMapping("/{id}")
    public PostResponseDto updatePost(
            @PathVariable Long id,
            @RequestBody PostUpdateDto dto) {

        UpdatePostRequest req = UpdatePostRequest.newBuilder()
                .setId(id)
                .setTitle(dto.getTitle())
                .setContent(dto.getContent())
                .setIsPrivate(dto.isPrivate())
                .addAllTags(dto.getTags() != null ? dto.getTags() : Collections.emptyList())
                .build();

        PostResponse grpc = postStub.updatePost(req);
        return map(grpc);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        GetPostRequest req = GetPostRequest.newBuilder()
                .setId(id)
                .build();
        postStub.deletePost(req);
    }

    private PostResponseDto map(PostResponse grpc) {
        return new PostResponseDto(
                grpc.getId(),
                grpc.getTitle(),
                grpc.getContent(),
                grpc.getAuthorId(),
                grpc.getCreatedAt(),
                grpc.getUpdatedAt(),
                grpc.getIsPrivate(),
                grpc.getTagsList()
        );
    }
}
