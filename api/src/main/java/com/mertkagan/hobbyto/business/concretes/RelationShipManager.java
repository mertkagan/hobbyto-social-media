package com.mertkagan.hobbyto.business.concretes;

import com.mertkagan.hobbyto.business.abstracts.RelationShipService;
import com.mertkagan.hobbyto.business.abstracts.UserService;
import com.mertkagan.hobbyto.business.requests.CreateRelationShipsRequest;
import com.mertkagan.hobbyto.business.responses.RelationShipsResponseByFollowedUserId;
import com.mertkagan.hobbyto.core.utilities.mappers.ModelMapperService;
import com.mertkagan.hobbyto.dataAccess.abstracts.RelationShipRepository;
import com.mertkagan.hobbyto.entities.concretes.Like;
import com.mertkagan.hobbyto.entities.concretes.RelationShip;
import com.mertkagan.hobbyto.entities.concretes.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RelationShipManager implements RelationShipService {

    private RelationShipRepository relationShipRepository;
    private UserService userService;
    private ModelMapperService modelMapperService;


    @Override
    public List<Long> getAllRelationShipByFollowedUserId(Long followedUserId) {
        List<RelationShip> relationShips = relationShipRepository.findByFollowedUserId_Id(followedUserId);
        List<Long> followerUserIds = new ArrayList<>();
        for (RelationShip relationShip : relationShips) {
            followerUserIds.add(relationShip.getFollowerUser().getId());
        }
        return followerUserIds;

    }

    @Override
    public RelationShip createRelationShip(CreateRelationShipsRequest createRelationShipsRequest) {

        User followedUser = userService.getUserById(createRelationShipsRequest.getFollowedUserId());
        User followerUserId = userService.getUserById(createRelationShipsRequest.getFollowerUserId());

        if(followedUser != null && followerUserId != null){
            RelationShip relationShip = this.modelMapperService.forRequest().map(createRelationShipsRequest, RelationShip.class);
            this.relationShipRepository.save(relationShip);
            return relationShip;
        }else {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteRelationShipByFollowedUserIdAndFollowerUserId(Long followedUserId, Long followerUserId) {
        relationShipRepository.deleteByFollowedUserIdAndFollowerUserId(followedUserId, followerUserId);
    }


}
