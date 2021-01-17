package com.bug_tracker.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthentication implements Authentication {

    private Long id;
    private Collection<GrantedAuthority> authorities;
    private boolean isAuthenticated;

    public JwtAuthentication(Long id, Collection<String> authoritiesStr) {
        this.id = id;
        this.authorities = new ArrayList<>();
        authoritiesStr.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        isAuthenticated = true;
    }

    public JwtAuthentication(Long id, String authority) {
        this.id = id;
        this.authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authority));
        isAuthenticated = true;
    }

    public long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
