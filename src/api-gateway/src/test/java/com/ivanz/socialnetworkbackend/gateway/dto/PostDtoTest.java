package com.ivanz.socialnetworkbackend.gateway.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PostDtoTest {

    @Test
    void postCreateDto_settersAndGetters() {
        PostCreateDto dto = new PostCreateDto();
        dto.setTitle("title");
        dto.setContent("content");
        dto.setAuthorId(99L);
        dto.setPrivate(true);
        dto.setTags(List.of("java"));

        assertThat(dto)
                .extracting(PostCreateDto::getTitle,
                        PostCreateDto::getContent,
                        PostCreateDto::getAuthorId,
                        PostCreateDto::isPrivate,
                        PostCreateDto::getTags)
                .containsExactly("title", "content", 99L, true, List.of("java"));
    }

    @Test
    void postUpdateDto_settersAndGetters() {
        PostUpdateDto dto = new PostUpdateDto();
        dto.setTitle("updated");
        dto.setContent("updated-content");
        dto.setPrivate(false);
        dto.setTags(List.of());

        assertThat(dto.isPrivate()).isFalse();
        assertThat(dto.getTitle()).isEqualTo("updated");
    }

    @Test
    void postResponseDto_allArgsConstructorAndEqualsHashCode() {
        List<String> tags = List.of("t1", "t2");
        PostResponseDto dto1 = new PostResponseDto(1L, "t", "c", 7L,
                "created", "updated", false, tags);
        PostResponseDto dto2 = new PostResponseDto(1L, "t", "c", 7L,
                "created", "updated", false, tags);

        assertThat(dto1).isEqualTo(dto2)
                .hasSameHashCodeAs(dto2);

        assertThat(dto1.getAuthorId()).isEqualTo(7L);
        assertThat(dto1.getTags()).containsExactly("t1", "t2");
    }
}
