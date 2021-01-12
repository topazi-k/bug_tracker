package com.bug_tracker.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    AuthenticationManager manager;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        System.out.println("Inside filter do filter");
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println("in doFilter InitialAuthenticationFilter");
        manager.authenticate(auth);


        //TODO: generate JWT
        //
        //??????? save JWT on server do   // find how to do
        //
        System.out.println("Auth success !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        httpServletResponse.setHeader("CustomHeadr", "authentic");
        httpServletResponse.setHeader("Authorization", "JWT");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        System.out.println("I custom filter shouldNotFilter(HttpServletRequest request)");
        System.out.println("URL: = = =   " +  request.getRequestURI());
        System.out.println(!request.getServletPath().equals("/login"));
        return !request.getServletPath().equals("/login");
    }
}
