package com.mertkagan.hobbyto.business.concretes;

import com.mertkagan.hobbyto.business.abstracts.AttendeeService;
import com.mertkagan.hobbyto.business.abstracts.PostService;
import com.mertkagan.hobbyto.business.abstracts.UserService;
import com.mertkagan.hobbyto.business.requests.CreateAttendeeRequest;
import com.mertkagan.hobbyto.core.utilities.mappers.ModelMapperService;
import com.mertkagan.hobbyto.dataAccess.abstracts.AttendeeRepository;
import com.mertkagan.hobbyto.entities.concretes.Attendee;
import com.mertkagan.hobbyto.entities.concretes.Like;
import com.mertkagan.hobbyto.entities.concretes.Post;
import com.mertkagan.hobbyto.entities.concretes.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendeeManager implements AttendeeService {

    private AttendeeRepository attendeeRepository;

    private PostService postService;

    private UserService userService;

    private ModelMapperService modelMapperService;


    @Override
    public List<Long> getAllAteendeesByPostId(Long postId) {
        List<Attendee> attendees = attendeeRepository.findByPostId(postId);
        List<Long> userIds = new ArrayList<>();
        for (Attendee attendee : attendees) {
            userIds.add(attendee.getUser().getId());
        }
        return userIds;
    }

    @Override
    public Attendee createAttendee(CreateAttendeeRequest createAttendeeRequest) {
        User user = userService.getUserById(createAttendeeRequest.getUserId());
        Post post = postService.getPostById(createAttendeeRequest.getPostId());

        if(user != null && post != null ){
            Attendee attendee = this.modelMapperService.forRequest().map(createAttendeeRequest, Attendee.class);
            this.attendeeRepository.save(attendee);
            return attendee;
        }else {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteAttendeeByPostIdAndUserId(Long postId, Long userId) {
        attendeeRepository.deleteByPostIdAndUserId(postId,userId);
    }
}
