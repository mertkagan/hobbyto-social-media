package com.mertkagan.hobbyto.dataAccess.abstracts;

import com.mertkagan.hobbyto.entities.concretes.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserId(Long userId);


    @Query("SELECT p FROM Post p JOIN p.user u LEFT JOIN RelationShip r ON (p.user.id = r.followedUser.id) WHERE r.followerUser.id = :followerUserId OR p.user.id = :userId ORDER BY p.creationDate DESC")
    List<Post> getPostsByFollowerOrUser(@Param("userId") Long userId ,@Param("followerUserId") Long followerUserId);

    List<Post> findByUserIdOrderByCreationDateDesc(Long userId);

    Post findByIdAndUserId(Long postId, Long userId);

    void deleteByIdAndUserId(Long postId, Long userId);
}
