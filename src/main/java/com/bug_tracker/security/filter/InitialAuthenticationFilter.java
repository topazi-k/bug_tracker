package com.bug_tracker.security.filter;

import com.bug_tracker.security.UserSecurity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bug_tracker.security.SecurityConstants.*;

@Component
public class InitialAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        return this.getAuthenticationManager().authenticate(auth);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        Map<String, Object> claims = getClaims(authResult);
        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
        response.setHeader(AUTHORIZATION, jwt);
        response.setStatus(200);
    }

    private Map<String, Object> getClaims(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();
        UserSecurity user = (UserSecurity) authentication.getPrincipal();
        List<String> authoritiesssss = new ArrayList<>();
        user.getAuthorities().forEach(authority -> authoritiesssss.add(authority.getAuthority()));
        claims.put(ID, Long.toString(user.getUserId()));
        claims.put(ROLE, authoritiesssss);
        System.out.println(claims);
        return claims;
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

}
