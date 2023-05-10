package com.mertkagan.hobbyto.dataAccess.abstracts;

import com.mertkagan.hobbyto.entities.concretes.RelationShip;
import com.mertkagan.hobbyto.entities.concretes.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RelationShipRepository extends JpaRepository<RelationShip,Long> {

    List<RelationShip> findByFollowedUserId_Id(Long followedUserId);


    @Modifying
    @Transactional
    @Query("DELETE FROM RelationShip r WHERE r.followedUser.id = :followedUserId AND r.followerUser.id = :followerUserId")
    void deleteByFollowedUserIdAndFollowerUserId(@Param("followedUserId") Long followedUserId, @Param("followerUserId") Long followerUserId);



}
