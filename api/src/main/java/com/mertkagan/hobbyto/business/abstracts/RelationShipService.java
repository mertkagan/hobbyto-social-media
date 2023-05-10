package com.mertkagan.hobbyto.business.abstracts;

import com.mertkagan.hobbyto.business.requests.CreateRelationShipsRequest;
import com.mertkagan.hobbyto.business.responses.RelationShipsResponseByFollowedUserId;
import com.mertkagan.hobbyto.entities.concretes.RelationShip;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RelationShipService {

    List<Long> getAllRelationShipByFollowedUserId(Long followedUserId);


    RelationShip createRelationShip(CreateRelationShipsRequest createRelationShipsRequest);

    void deleteRelationShipByFollowedUserIdAndFollowerUserId(Long followedUser, Long followerUser);


}
