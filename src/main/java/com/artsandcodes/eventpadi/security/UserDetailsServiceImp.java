package com.artsandcodes.eventpadi.security;

import com.swifta.omnibranches.model.User;
import com.swifta.omnibranches.service.UserService;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by prestige on 11/27/2017.
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {

    UserService userService;

    public UserDetailsServiceImp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = this.userService.findByEmailOrMobile(s);

        if(user == null){
            throw new UsernameNotFoundException("Username or password incorrect");
        }
        else if(user.getStatus() == 0){
            throw new InsufficientAuthenticationException("Account is inactive");
        }
        else if(user.getDisabled() == 1){
            throw new InsufficientAuthenticationException("Account is disabled");
        }
        else if(user.getDeleted() == 1){
            throw new UsernameNotFoundException("Username or password incorrect");
        }

        return new UserDetail(user);

    }


}
