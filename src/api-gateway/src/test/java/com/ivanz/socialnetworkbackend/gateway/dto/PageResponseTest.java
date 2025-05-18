package com.ivanz.socialnetworkbackend.gateway.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PageResponseTest {

    @Test
    void constructorAndSettersWorkAsExpected() {
        PageResponse<String> page = new PageResponse<>(List.of("A", "B"), 2, 1);

        assertThat(page.getContent()).containsExactly("A", "B");
        assertThat(page.getTotalElements()).isEqualTo(2);
        assertThat(page.getTotalPages()).isEqualTo(1);

        page.setContent(List.of("C"));
        page.setTotalElements(1);
        page.setTotalPages(5);

        assertThat(page).extracting(PageResponse::getContent,
                        PageResponse::getTotalElements,
                        PageResponse::getTotalPages)
                .containsExactly(List.of("C"), 1L, 5);
    }
}
