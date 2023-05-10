package com.mertkagan.hobbyto.business.requests;

import lombok.Data;

@Data
public class CreateLikeRequest {

    private Long userId;

    private Long postId;
}
