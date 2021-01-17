package com.bug_tracker.security;

import com.bug_tracker.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserSecurity implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private User user;
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public UserSecurity(User user) {
        this.user = user;
        this.authorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return user.isEnabled();
    }

    public long getUserId() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }

}
