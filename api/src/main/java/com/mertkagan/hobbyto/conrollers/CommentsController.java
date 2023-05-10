package com.mertkagan.hobbyto.conrollers;

import com.mertkagan.hobbyto.business.abstracts.CommentService;
import com.mertkagan.hobbyto.business.requests.CreateCommentRequest;
import com.mertkagan.hobbyto.business.responses.CommentsResponse;
import com.mertkagan.hobbyto.entities.concretes.Comment;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@AllArgsConstructor
public class CommentsController {

    private CommentService commentService;

    @GetMapping
    public List<CommentsResponse> getAllComments(@RequestParam Optional<Long> userId, Optional<Long> postId){
        return commentService.getAllCommentsWithParam(userId,postId);
    }

    @PostMapping("/createComment")
    public Comment createOneComment(@RequestBody CreateCommentRequest createCommentRequest){
        return commentService.createOneComment(createCommentRequest);
    }
}
