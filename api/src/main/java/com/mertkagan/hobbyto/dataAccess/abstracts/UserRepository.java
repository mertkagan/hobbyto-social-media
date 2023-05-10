package com.mertkagan.hobbyto.dataAccess.abstracts;

import com.mertkagan.hobbyto.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);



    Optional<User> findOneByUserNameAndPassword(String userName, String encodedPassword);


    User findByEmail(String email);
}
