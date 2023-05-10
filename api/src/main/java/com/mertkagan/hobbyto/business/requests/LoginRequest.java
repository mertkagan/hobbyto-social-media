package com.mertkagan.hobbyto.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest {

    private String userName;
    private String password;
}
