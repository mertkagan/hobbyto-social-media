package com.mertkagan.hobbyto.business.abstracts;

import com.mertkagan.hobbyto.business.requests.CreateUsersRequest;
import com.mertkagan.hobbyto.business.requests.LoginRequest;
import com.mertkagan.hobbyto.business.requests.UpdateUserRequestByUserId;
import com.mertkagan.hobbyto.business.responses.LoginResponse;
import com.mertkagan.hobbyto.business.responses.UserResponse;
import com.mertkagan.hobbyto.business.responses.UserResponseByUserId;
import com.mertkagan.hobbyto.entities.concretes.User;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String createOneUser(CreateUsersRequest createUsersRequest, BindingResult bindingResult);


    LoginResponse loginUser(LoginRequest loginRequest);


    User getUserById(Long userId);

    List<UserResponse> getAllUsers();


    void updateOneUserByUserId(Long userId, UpdateUserRequestByUserId updateUserRequestByUserId);


}
