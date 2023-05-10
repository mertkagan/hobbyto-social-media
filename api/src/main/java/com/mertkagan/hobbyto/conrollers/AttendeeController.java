package com.mertkagan.hobbyto.conrollers;

import com.mertkagan.hobbyto.business.abstracts.AttendeeService;
import com.mertkagan.hobbyto.business.requests.CreateAttendeeRequest;
import com.mertkagan.hobbyto.business.requests.CreateLikeRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendees")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AttendeeController {

    private AttendeeService attendeeService;

    @GetMapping
    public List<Long> getAllAteendeesByPostId(@RequestParam Long postId){
        return attendeeService.getAllAteendeesByPostId(postId);
    }

    @PostMapping("/createAttendee")
    public ResponseEntity<?> createAttendee(@RequestBody CreateAttendeeRequest createAttendeeRequest){

        try {
            attendeeService.createAttendee(createAttendeeRequest);
            return ResponseEntity.ok("Katılım Başarılı!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Katılım Başarısız!");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAttendeeByPostIdAndUserId(@RequestParam Long postId, @RequestParam Long userId) {
        try {
            attendeeService.deleteAttendeeByPostIdAndUserId(postId, userId);
            return ResponseEntity.ok("Katılımcı silindi!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Katılımcı silinemedi!");
        }
    }
}
