package com.mertkagan.hobbyto.business.concretes;

import com.mertkagan.hobbyto.business.abstracts.CommentService;
import com.mertkagan.hobbyto.business.abstracts.PostService;
import com.mertkagan.hobbyto.business.abstracts.UserService;
import com.mertkagan.hobbyto.business.requests.CreateCommentRequest;
import com.mertkagan.hobbyto.business.responses.CommentsResponse;
import com.mertkagan.hobbyto.business.responses.PostsResponse;
import com.mertkagan.hobbyto.core.utilities.mappers.ModelMapperService;
import com.mertkagan.hobbyto.dataAccess.abstracts.CommentRepository;
import com.mertkagan.hobbyto.entities.concretes.Comment;
import com.mertkagan.hobbyto.entities.concretes.Post;
import com.mertkagan.hobbyto.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentManager implements CommentService {

    private CommentRepository commentRepository;
    private UserService userService;

    private PostService postService;

    private ModelMapperService modelMapperService;
    @Override
    public List<CommentsResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> comments;
        if (userId.isPresent() && postId.isPresent()){
            comments =commentRepository.findByUserIdAndPostIdOrderByCreatedAtDesc(userId.get(),postId.get());
            List<CommentsResponse> response = comments.stream()
                    .map(comment -> this.modelMapperService.forResponse().map(comment, CommentsResponse.class))
                    .collect(Collectors.toList());
            return response;
        }else if(userId.isPresent()){
            comments = commentRepository.findByUserIdOrderByCreatedAtDesc(userId.get());
            List<CommentsResponse> response = comments.stream()
                    .map(comment -> this.modelMapperService.forResponse().map(comment, CommentsResponse.class))
                    .collect(Collectors.toList());
            return response;

        }else if(postId.isPresent()){
            comments =  commentRepository.findByPostIdOrderByCreatedAtDesc(postId.get());
            List<CommentsResponse> response = comments.stream()
                    .map(comment -> this.modelMapperService.forResponse().map(comment, CommentsResponse.class))
                    .collect(Collectors.toList());
            return response;
        }else {
            comments = commentRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
            List<CommentsResponse> response = comments.stream()
                    .map(comment -> this.modelMapperService.forResponse().map(comment, CommentsResponse.class))
                    .collect(Collectors.toList());
            return response;

        }
    }

    @Override
    public Comment createOneComment(CreateCommentRequest createCommentRequest) {
        User user = userService.getUserById(createCommentRequest.getUserId());
        Post post = postService.getPostById(createCommentRequest.getPostId());
        if(user != null && post != null){
            Comment comment = this.modelMapperService.forRequest().map(createCommentRequest, Comment.class);
            this.commentRepository.save(comment);
            return comment;
        }else {
            return  null;
        }
    }
}
