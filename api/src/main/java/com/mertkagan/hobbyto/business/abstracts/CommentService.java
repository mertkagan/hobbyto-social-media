package com.mertkagan.hobbyto.business.abstracts;

import com.mertkagan.hobbyto.business.requests.CreateCommentRequest;
import com.mertkagan.hobbyto.business.responses.CommentsResponse;
import com.mertkagan.hobbyto.entities.concretes.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<CommentsResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId);

    Comment createOneComment(CreateCommentRequest createCommentRequest);
}
