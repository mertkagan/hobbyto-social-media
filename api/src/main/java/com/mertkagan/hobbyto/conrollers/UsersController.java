package com.mertkagan.hobbyto.conrollers;

import com.mertkagan.hobbyto.business.abstracts.UserService;
import com.mertkagan.hobbyto.business.requests.CreateUsersRequest;
import com.mertkagan.hobbyto.business.requests.LoginRequest;
import com.mertkagan.hobbyto.business.requests.UpdateUserRequestByUserId;
import com.mertkagan.hobbyto.business.responses.LoginResponse;
import com.mertkagan.hobbyto.business.responses.UserResponse;
import com.mertkagan.hobbyto.business.responses.UserResponseByUserId;
import com.mertkagan.hobbyto.entities.concretes.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private UserService userService;

    @PostMapping("/signUp")
    public String createOneUser(@Valid @RequestBody CreateUsersRequest createUsersRequest, BindingResult bindingResult){
         return userService.createOneUser(createUsersRequest,bindingResult);


    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

        LoginResponse loginResponse = userService.loginUser(loginRequest);
        return ResponseEntity.ok(loginResponse);


    }

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/update")
    public void updateOneUserByUserId(@RequestParam Long userId, @RequestBody UpdateUserRequestByUserId updateUserRequestByUserId){
        userService.updateOneUserByUserId(userId,updateUserRequestByUserId);
    }


}
