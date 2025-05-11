package com.ivanz.socialnetworkbackend.content.grpc;

import com.google.protobuf.Empty;
import com.ivanz.socialnetworkbackend.content.model.Post;
import com.ivanz.socialnetworkbackend.content.service.PostService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.PageRequest;

@GrpcService
@RequiredArgsConstructor
public class PostServiceGrpcImpl extends PostServiceGrpc.PostServiceImplBase {

    private final PostService postService;

    @Override
    public void createPost(CreatePostRequest request, StreamObserver<PostResponse> responseObserver) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthorId(request.getAuthorId());
        post.setPrivate(request.getIsPrivate());
        post.setTags(request.getTagsList());

        Post saved = postService.createPost(post);
        responseObserver.onNext(toGrpc(saved));
        responseObserver.onCompleted();
    }

    @Override
    public void getPostById(GetPostRequest request, StreamObserver<PostResponse> responseObserver) {
        Post post = postService.getPostById(request.getId());
        responseObserver.onNext(toGrpc(post));
        responseObserver.onCompleted();
    }

    @Override
    public void updatePost(UpdatePostRequest request, StreamObserver<PostResponse> responseObserver) {
        Post update = new Post();
        update.setTitle(request.getTitle());
        update.setContent(request.getContent());
        update.setAuthorId(request.getAuthorId());
        update.setPrivate(request.getIsPrivate());
        update.setTags(request.getTagsList());

        Post updated = postService.updatePost(request.getId(), update);
        responseObserver.onNext(toGrpc(updated));
        responseObserver.onCompleted();
    }

    @Override
    public void deletePost(GetPostRequest request, StreamObserver<Empty> responseObserver) {
        postService.deletePost(request.getId());
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllPosts(PaginatedRequest request, StreamObserver<PaginatedPostResponse> responseObserver) {
        var page = postService.getAllPosts(PageRequest.of(request.getPage(), request.getSize()));

        PaginatedPostResponse response = PaginatedPostResponse.newBuilder()
                .addAllPosts(page.getContent().stream().map(this::toGrpc).toList())
                .setTotalElements(page.getTotalElements())
                .setTotalPages(page.getTotalPages())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private PostResponse toGrpc(Post post) {
        return PostResponse.newBuilder()
                .setId(post.getId())
                .setTitle(post.getTitle())
                .setContent(post.getContent())
                .setAuthorId(post.getAuthorId())
                .setCreatedAt(post.getCreatedAt().toString())
                .setUpdatedAt(post.getUpdatedAt().toString())
                .setIsPrivate(post.isPrivate())
                .addAllTags(post.getTags())
                .build();
    }
}
