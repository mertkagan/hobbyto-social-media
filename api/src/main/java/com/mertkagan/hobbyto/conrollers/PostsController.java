package com.mertkagan.hobbyto.conrollers;

import com.mertkagan.hobbyto.business.abstracts.PostService;
import com.mertkagan.hobbyto.business.requests.CreatePostRequest;
import com.mertkagan.hobbyto.business.responses.PostsResponse;
import com.mertkagan.hobbyto.entities.concretes.Post;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class PostsController {

    private PostService postService;

    @GetMapping()
    public List<PostsResponse> getAllPosts(@RequestParam(required = false) Long userId,@RequestParam(required = false) Long followerId){
        return postService.getAllPosts(userId,followerId);
    }

    @GetMapping("/byFollowerOrUser")
    public List<PostsResponse> getAllPostsByFollowerOrUser(@RequestParam Long currentUser) {
        return postService.getAllPostsByFollowerOrUser(currentUser);
    }


    @PostMapping("createPost")
    public Post createOnePost(@RequestBody CreatePostRequest createPostRequest){
        return postService.createOnePost(createPostRequest);

    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId){
       return postService.getPostById(postId);
    }

    @DeleteMapping("/delete")
    public String deletePost(@RequestParam Long postId){
        return postService.deletePost(postId);
    }

}
