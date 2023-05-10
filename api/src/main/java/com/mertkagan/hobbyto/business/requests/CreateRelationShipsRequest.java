package com.mertkagan.hobbyto.business.requests;

import lombok.Data;

@Data
public class CreateRelationShipsRequest {

    private Long followerUserId;

    private Long followedUserId;
}
