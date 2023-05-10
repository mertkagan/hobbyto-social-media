package com.mertkagan.hobbyto.business.responses;

import lombok.Data;

@Data
public class UserResponseByUserId {

    private Long id ;

    private String name;

    private String surName;

    private String userName;

    private String cityId;

    private String profilePic;

    private String coverPic;

}
