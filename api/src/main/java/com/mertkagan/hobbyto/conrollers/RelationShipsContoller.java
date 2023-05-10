package com.mertkagan.hobbyto.conrollers;

import com.mertkagan.hobbyto.business.abstracts.RelationShipService;
import com.mertkagan.hobbyto.business.abstracts.UserService;
import com.mertkagan.hobbyto.business.requests.CreateLikeRequest;
import com.mertkagan.hobbyto.business.requests.CreateRelationShipsRequest;
import com.mertkagan.hobbyto.business.responses.RelationShipsResponseByFollowedUserId;
import com.mertkagan.hobbyto.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relationShips")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class RelationShipsContoller {

    private RelationShipService relationShipService;


    @GetMapping
    public List<Long> getAllRelationShipByFollowedUserId(@RequestParam Long followedUserId){

        return relationShipService.getAllRelationShipByFollowedUserId(followedUserId);
    }

    @PostMapping
    public ResponseEntity<?> createRelationShip(@RequestBody CreateRelationShipsRequest createRelationShipsRequest){

        try {
            relationShipService.createRelationShip(createRelationShipsRequest);
            return ResponseEntity.ok("Takip Edildi!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Takip Edilemedi!");
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRelationShipByFollowedUserIdAndFollowerUserId(@RequestParam Long followedUserId, @RequestParam Long followerUserId) {
        try {
            relationShipService.deleteRelationShipByFollowedUserIdAndFollowerUserId(followedUserId, followerUserId);
            return ResponseEntity.ok("Takip İptal Edildi!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Takip İptal İşlemi Başarısız Oldu!" + e );
        }
    }
}
