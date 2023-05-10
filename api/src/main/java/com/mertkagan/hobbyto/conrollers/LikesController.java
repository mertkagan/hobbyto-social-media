package com.mertkagan.hobbyto.conrollers;

import com.mertkagan.hobbyto.business.abstracts.LikeService;
import com.mertkagan.hobbyto.business.requests.CreateLikeRequest;
import com.mertkagan.hobbyto.business.responses.LikeResponseByPostId;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@AllArgsConstructor
public class LikesController {

    private LikeService likeService;

    @GetMapping
    public List<Long> getAllLikesByPostId(@RequestParam Long postId){
        return likeService.getAllLikesByPostId(postId);
    }

    @PostMapping
    public ResponseEntity<?> createLike(@RequestBody CreateLikeRequest createLikeRequest){

        try {
            likeService.createLike(createLikeRequest);
            return ResponseEntity.ok("Beğeni Eklendi!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Beğeni Eklenemedi!");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteLikeByPostIdAndUserId(@RequestParam Long postId, @RequestParam Long userId) {
        try {
            likeService.deleteByPostIdAndUserId(postId, userId);
            return ResponseEntity.ok("Beğeni silindi!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Beğeni silinemedi!");
        }
    }
}
