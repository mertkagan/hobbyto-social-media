package com.mertkagan.hobbyto.business.abstracts;

import com.mertkagan.hobbyto.business.requests.CreateAttendeeRequest;
import com.mertkagan.hobbyto.entities.concretes.Attendee;

import java.util.List;

public interface AttendeeService {
    List<Long> getAllAteendeesByPostId(Long postId);

    Attendee createAttendee(CreateAttendeeRequest createAttendeeRequest);


    void deleteAttendeeByPostIdAndUserId(Long postId, Long userId);
}
