package com.mertkagan.hobbyto.business.concretes;

import com.mertkagan.hobbyto.business.abstracts.LikeService;
import com.mertkagan.hobbyto.business.abstracts.PostService;
import com.mertkagan.hobbyto.business.abstracts.UserService;
import com.mertkagan.hobbyto.business.requests.CreateLikeRequest;
import com.mertkagan.hobbyto.business.responses.LikeResponseByPostId;
import com.mertkagan.hobbyto.business.responses.PostsResponse;
import com.mertkagan.hobbyto.core.utilities.mappers.ModelMapperService;
import com.mertkagan.hobbyto.dataAccess.abstracts.LikeRepository;
import com.mertkagan.hobbyto.entities.concretes.Like;
import com.mertkagan.hobbyto.entities.concretes.Post;
import com.mertkagan.hobbyto.entities.concretes.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LikeManager implements LikeService {

    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;
    private ModelMapperService modelMapperService;

    @Override
    public List<Long> getAllLikesByPostId(Long postId) {

        List<Like> likes = likeRepository.findByPostId(postId);
        List<Long> userIds = new ArrayList<>();
        for (Like like : likes) {
            userIds.add(like.getUser().getId());
        }
        return userIds;

    }

    @Override
    @Transactional
    public void deleteByPostIdAndUserId(Long postId, Long userId) {
        likeRepository.deleteByPostIdAndUserId(postId, userId);
    }

    @Override
    public Like createLike(CreateLikeRequest createLikeRequest) {
        User user = userService.getUserById(createLikeRequest.getUserId());
        Post post = postService.getPostById(createLikeRequest.getPostId());

        if(user != null && post != null ){
            Like like = this.modelMapperService.forRequest().map(createLikeRequest, Like.class);
            this.likeRepository.save(like);
            return like;
        }else {
            return null;
        }

    }
}
