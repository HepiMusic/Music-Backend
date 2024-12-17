package com.hepi.music_api.security.common.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private String message;
    private int code;
    private T body;

    public static <T> ApiResponse<T> success(String message, T body) {
        return ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .code(200)
                .body(body)
                .build();
    }

    public static <T> ApiResponse<T> error(String message, int code) {
        return ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .code(code)
                .body(null)
                .build();
    }
}
