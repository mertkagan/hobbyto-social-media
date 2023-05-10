package com.mertkagan.hobbyto.business.abstracts;

import com.mertkagan.hobbyto.business.requests.CreateLikeRequest;
import com.mertkagan.hobbyto.business.responses.LikeResponseByPostId;
import com.mertkagan.hobbyto.entities.concretes.Like;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LikeService {

    List<Long> getAllLikesByPostId(Long postId);

    void deleteByPostIdAndUserId(Long postId, Long userId);

    Like createLike(CreateLikeRequest createLikeRequest);
}
