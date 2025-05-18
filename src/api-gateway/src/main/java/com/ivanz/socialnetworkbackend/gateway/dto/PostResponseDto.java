package com.ivanz.socialnetworkbackend.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private String createdAt;
    private String updatedAt;
    private boolean isPrivate;
    private List<String> tags;
}
