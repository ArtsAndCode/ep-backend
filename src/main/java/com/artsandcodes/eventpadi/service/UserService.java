package com.artsandcodes.eventpadi.service;

import com.swifta.omnibranches.model.Role;
import com.swifta.omnibranches.model.User;
import com.swifta.omnibranches.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by prestige on 11/17/2017.
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user){

        return this.userRepository.save(user);
    }

    public List<User> findAll(){

        return this.userRepository.findAll();
    }

    public User findById(long userId){

        return this.userRepository.findOne(userId);
    }

    public User findByEmail(String email){

        return this.userRepository.findUserByEmail(email);
    }

    public User findByMobile(String mobile){

        return this.userRepository.findUserByMobile(mobile);
    }

    public User findByEmailOrMobile(String emailOrMobile){

        return this.userRepository.findUserByEmailOrMobile(emailOrMobile, emailOrMobile);
    }

    public User findByEmailOrMobile(String email, String mobile){

        return this.userRepository.findUserByEmailOrMobile(email, mobile);
    }

    public User findByEmailAndPassword(String email, String password){

        return  this.userRepository.findUserByEmailAndPassword(email,password);
    }

    public List<User> findByRole(Role role){

        return this.userRepository.findUsersByRoleAndDeleted(role,(short)0);
    }

    public List<User> findUsersByStatusAndDeletedAndDisabled(short status, short deleted, short disabled){

        return this.userRepository.findUsersByStatusAndDeletedAndDisabled(status, deleted, disabled);
    }
}
