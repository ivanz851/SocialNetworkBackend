package com.ivanz.socialnetworkbackend.content.grpc;

import com.google.protobuf.Empty;
import com.ivanz.socialnetworkbackend.content.model.Post;
import com.ivanz.socialnetworkbackend.content.service.PostService;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceGrpcImplTest {

    @Mock private PostService postService;
    @Mock private io.grpc.stub.StreamObserver<PostResponse>        postObserver;
    @Mock private io.grpc.stub.StreamObserver<PaginatedPostResponse> pageObserver;
    @Mock private io.grpc.stub.StreamObserver<Empty>               emptyObserver;

    private PostServiceGrpcImpl grpc;

    @BeforeEach
    void init() { grpc = new PostServiceGrpcImpl(postService); }

    private static Post sample() {
        Post p = new Post();
        p.setId(1L);
        p.setTitle("t");
        p.setContent("c");
        p.setAuthorId(7L);
        return p;
    }

    /* ---------- createPost ---------- */

    @Test
    void createPost_mapsFieldsAndReturnsGrpc() {
        Post created = sample();
        when(postService.createPost(any())).thenReturn(created);

        CreatePostRequest req = CreatePostRequest.newBuilder()
                .setTitle("t").setContent("c").setAuthorId(7L).build();

        grpc.createPost(req, postObserver);

        ArgumentCaptor<Post> captor = ArgumentCaptor.forClass(Post.class);
        verify(postService).createPost(captor.capture());
        assertThat(captor.getValue().getAuthorId()).isEqualTo(7L);

        verify(postObserver).onNext(argThat(r -> r.getId() == 1L));
        verify(postObserver).onCompleted();
    }

    /* ---------- getPostById success ---------- */

    @Test
    void getPostById_success() {
        when(postService.getPostById(1L)).thenReturn(sample());

        grpc.getPostById(GetPostRequest.newBuilder().setId(1L).build(), postObserver);

        verify(postObserver).onNext(any(PostResponse.class));
        verify(postObserver).onCompleted();
    }

    /* ---------- getPostById not-found ---------- */

    @Test
    void getPostById_notFound_translatesToGrpc404() {
        when(postService.getPostById(1L))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "no"));

        grpc.getPostById(GetPostRequest.newBuilder().setId(1L).build(), postObserver);

        ArgumentCaptor<Throwable> captor = ArgumentCaptor.forClass(Throwable.class);
        verify(postObserver).onError(captor.capture());
        assertThat(captor.getValue()).isInstanceOf(StatusRuntimeException.class)
                .hasMessageContaining("NOT_FOUND");
    }

    /* ---------- getAllPosts ---------- */

    @Test
    void getAllPosts_mapsPageInfo() {
        Page<Post> page = new PageImpl<>(List.of(sample()), PageRequest.of(0, 2), 1);
        when(postService.getAllPosts(PageRequest.of(0, 2))).thenReturn(page);

        PaginatedRequest req = PaginatedRequest.newBuilder().setPage(0).setSize(2).build();
        grpc.getAllPosts(req, pageObserver);

        verify(pageObserver).onNext(argThat(r ->
                r.getTotalElements() == 1 && r.getTotalPages() == 1));
        verify(pageObserver).onCompleted();
    }

    /* ---------- deletePost ---------- */

    @Test
    void deletePost_callsServiceAndReturnsEmpty() {
        grpc.deletePost(GetPostRequest.newBuilder().setId(1L).build(), emptyObserver);
        verify(postService).deletePost(1L);
        verify(emptyObserver).onNext(Empty.newBuilder().build());
        verify(emptyObserver).onCompleted();
    }
}
