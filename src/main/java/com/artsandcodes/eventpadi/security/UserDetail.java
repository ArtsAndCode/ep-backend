package com.artsandcodes.eventpadi.security;

import com.swifta.omnibranches.model.Permission;
import com.swifta.omnibranches.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by prestige on 11/27/2017.
 */

public class UserDetail implements UserDetails {

    private User user;

    public UserDetail(User user) {

        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        List<Permission> permissions = this.user.getRole().getPermissions();

        for(Permission permission : permissions){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission.getName());
            authorities.add(simpleGrantedAuthority);
        }

        System.out.println("****** Authoriesties ******* : " + authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.getStatus() == 1;
    }
}
