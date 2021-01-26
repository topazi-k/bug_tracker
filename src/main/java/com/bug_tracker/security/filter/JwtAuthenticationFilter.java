package com.bug_tracker.security.filter;

import com.bug_tracker.security.JwtAuthentication;
import com.bug_tracker.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bug_tracker.constants.SecurityConstants.*;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt;
        if (request.getServletPath().equals(EMAIL_CONFIRM_URL)) {
            jwt = request.getParameter(TOKEN_PARAM);
        } else if (request.getHeader(AUTHORIZATION) == null) {
            SecurityContextHolder.getContext().setAuthentication(null);
            filterChain.doFilter(request, response);
            return;
        } else {
            jwt = request.getHeader(AUTHORIZATION);
        }

        JwtAuthentication authentication;
        try {
            authentication = tokenService.parseToken(jwt);
        } catch (Exception e) {
            throw new BadCredentialsException("Authentication failed");
        }
        response.setHeader(AUTHORIZATION, jwt);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals(LOGIN_URL);
    }

    @Autowired
    public void setTokenService(JwtTokenService tokenService) {
        this.tokenService = tokenService;
    }
}


