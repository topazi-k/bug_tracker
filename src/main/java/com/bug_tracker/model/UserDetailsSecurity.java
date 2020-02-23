package com.bug_tracker.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsSecurity implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private User user;
    private List<GrantedAuthority> athorities=new ArrayList<>();

    public UserDetailsSecurity(User user) {
        this.user = user;
        this.athorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));
    }

    public UserDetailsSecurity() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return athorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
