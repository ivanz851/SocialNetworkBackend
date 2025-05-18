package com.ivanz.socialnetworkbackend.gateway.controller;

import com.ivanz.socialnetworkbackend.content.grpc.*;
import com.ivanz.socialnetworkbackend.gateway.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostServiceGrpc.PostServiceBlockingStub stub;

    private PostController controller;

    @BeforeEach
    void setUp() {
        controller = new PostController();
        ReflectionTestUtils.setField(controller, "postStub", stub);
    }

    /* ---------- helpers ---------- */

    private static PostResponse samplePost() {
        return PostResponse.newBuilder()
                .setId(42L)
                .setTitle("title")
                .setContent("content")
                .setAuthorId(7L)
                .setCreatedAt("2025-05-18T12:00:00Z")
                .setUpdatedAt("2025-05-18T13:00:00Z")
                .setIsPrivate(false)
                .addTags("tag")
                .build();
    }

    private static PostCreateDto createDto() {
        PostCreateDto dto = new PostCreateDto();
        dto.setTitle("title");
        dto.setContent("content");
        dto.setAuthorId(7L);
        dto.setPrivate(false);
        dto.setTags(List.of("tag"));
        return dto;
    }

    private static PostUpdateDto updateDto() {
        PostUpdateDto dto = new PostUpdateDto();
        dto.setTitle("title");
        dto.setContent("content");
        dto.setPrivate(false);
        dto.setTags(List.of("tag"));
        return dto;
    }

    /* ---------- tests ---------- */

    @Test
    void createPost_sendsCorrectRequestAndMapsResponse() {
        when(stub.createPost(any(CreatePostRequest.class))).thenReturn(samplePost());

        PostResponseDto result = controller.createPost(createDto());

        ArgumentCaptor<CreatePostRequest> cap = ArgumentCaptor.forClass(CreatePostRequest.class);
        verify(stub).createPost(cap.capture());
        CreatePostRequest sent = cap.getValue();

        assertThat(sent.getAuthorId()).isEqualTo(7L);
        assertThat(sent.getTagsList()).containsExactly("tag");

        assertThat(result.getId()).isEqualTo(42L);
        assertThat(result.getTitle()).isEqualTo("title");
    }

    @Test
    void getPost_buildsRequestAndMapsResponse() {
        when(stub.getPostById(any(GetPostRequest.class))).thenReturn(samplePost());

        PostResponseDto dto = controller.getPost(42L);

        verify(stub).getPostById(GetPostRequest.newBuilder().setId(42L).build());
        assertThat(dto.getId()).isEqualTo(42L);
    }

    @Test
    void getAll_returnsPageResponseWithMappedPosts() {
        PaginatedPostResponse grpc = PaginatedPostResponse.newBuilder()
                .addPosts(samplePost())
                .setTotalElements(1)
                .setTotalPages(1)
                .build();
        when(stub.getAllPosts(any(PaginatedRequest.class))).thenReturn(grpc);

        PageResponse<PostResponseDto> page = controller.getAll(0, 10);

        verify(stub).getAllPosts(PaginatedRequest.newBuilder().setPage(0).setSize(10).build());
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getContent()).first().extracting(PostResponseDto::getId).isEqualTo(42L);
    }

    @Test
    void updatePost_sendsCorrectRequestAndMapsResponse() {
        when(stub.updatePost(any(UpdatePostRequest.class))).thenReturn(samplePost());

        PostResponseDto dto = controller.updatePost(42L, updateDto());

        ArgumentCaptor<UpdatePostRequest> cap = ArgumentCaptor.forClass(UpdatePostRequest.class);
        verify(stub).updatePost(cap.capture());
        assertThat(cap.getValue().getId()).isEqualTo(42L);
        assertThat(dto.getId()).isEqualTo(42L);
    }

    @Test
    void deletePost_invokesGrpcStubWithCorrectRequest() {
        controller.deletePost(42L);
        verify(stub).deletePost(GetPostRequest.newBuilder().setId(42L).build());
    }
}
