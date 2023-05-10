package com.mertkagan.hobbyto.business.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCommentRequest {
    private String commentText;

    private Long userId;

    private Long postId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt = LocalDateTime.now();
}
