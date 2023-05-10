package com.mertkagan.hobbyto.dataAccess.abstracts;

import com.mertkagan.hobbyto.entities.concretes.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

    List<Like> findByPostId(Long postId);

    void deleteByPostIdAndUserId(Long postId, Long userId);
}
