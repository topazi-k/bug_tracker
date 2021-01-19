package com.bug_tracker.security.filter;

import com.bug_tracker.model.User;
import com.bug_tracker.security.UserSecurity;
import com.bug_tracker.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bug_tracker.security.SecurityConstants.*;

@Component
public class InitialAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtTokenService tokenService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        System.out.println(username +" " + password);
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        return this.getAuthenticationManager().authenticate(auth);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User user = ((UserSecurity) authResult.getPrincipal()).getUser();
        String jwt = tokenService.createJwtToken(user);
        response.setHeader(AUTHORIZATION, jwt);
        response.setStatus(200);
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Autowired
    public void setTokenService(JwtTokenService tokenService) {
        this.tokenService = tokenService;
    }

}
