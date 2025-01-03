package com.hepi.music_api.comment.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentDTO {
    private Long id;
    private Long songId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;

    // Getters and Setters
    // Constructors
}
