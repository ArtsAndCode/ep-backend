package com.artsandcodes.eventpadi.repository;

import com.artsandcodes.eventpadi.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by prestige on 11/17/2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByEmail(String email);

    User findUserByMobile(String mobile);

    User findUserByEmailAndPassword(String email, String password);

    User findUserByEmailOrMobile(String emailOrMobile, String mobileOrEmail);

    List<User> findUsersByStatusAndDeletedAndDisabled(short status, short deleted, short disabled);
}
