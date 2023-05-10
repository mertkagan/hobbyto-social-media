package com.mertkagan.hobbyto.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestByUserId {

    private String email;

    private String name;

    private String surName;

    private Long cityId;

    private String profilePic;

    private String coverPic;


}
