package com.ivanz.socialnetworkbackend.gateway.dto;

import lombok.Data;
import java.util.List;

@Data
public class PostCreateDto {
    private String title;
    private String content;
    private Long authorId;
    private boolean isPrivate;
    private List<String> tags;
}
