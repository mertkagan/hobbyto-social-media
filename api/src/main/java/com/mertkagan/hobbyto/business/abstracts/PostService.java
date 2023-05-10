package com.mertkagan.hobbyto.business.abstracts;

import com.mertkagan.hobbyto.business.requests.CreatePostRequest;
import com.mertkagan.hobbyto.business.responses.PostsResponse;
import com.mertkagan.hobbyto.entities.concretes.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<PostsResponse> getAllPosts(Long userId,Long followerId);

    List<PostsResponse> getAllPostsByFollowerOrUser(Long currentUser);


    Post createOnePost(CreatePostRequest createPostRequest);

    Post getPostById(Long postId);


    String deletePost(Long postId);
}
