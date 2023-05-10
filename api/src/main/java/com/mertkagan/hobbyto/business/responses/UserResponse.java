package com.mertkagan.hobbyto.business.responses;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String name;

    private String surName;

    private String userName;

    private String email;

    private String cityName;

    private String profilePic;

    private String coverPic;


}
