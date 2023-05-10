package com.mertkagan.hobbyto.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginResponse {

    String message;
    Boolean status;

    Long userId;

    String userName;

    String profilePic;




}
