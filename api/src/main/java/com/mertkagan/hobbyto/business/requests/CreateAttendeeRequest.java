package com.mertkagan.hobbyto.business.requests;

import lombok.Data;

@Data
public class CreateAttendeeRequest {


    private Long userId;
    private Long postId;


}
