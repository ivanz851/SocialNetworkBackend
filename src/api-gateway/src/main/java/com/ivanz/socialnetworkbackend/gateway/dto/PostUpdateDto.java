package com.ivanz.socialnetworkbackend.gateway.dto;

import lombok.Data;
import java.util.List;

@Data
public class PostUpdateDto {
    private String title;
    private String content;
    private boolean isPrivate;
    private List<String> tags;
}
