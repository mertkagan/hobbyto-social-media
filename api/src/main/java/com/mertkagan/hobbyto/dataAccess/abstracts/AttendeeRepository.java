package com.mertkagan.hobbyto.dataAccess.abstracts;

import com.mertkagan.hobbyto.entities.concretes.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee,Long> {
    List<Attendee> findByPostId(Long postId);

    void deleteByPostIdAndUserId(Long postId, Long userId);
}
