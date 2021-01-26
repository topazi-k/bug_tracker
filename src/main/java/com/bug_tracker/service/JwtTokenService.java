package com.bug_tracker.service;

import com.bug_tracker.model.User;
import com.bug_tracker.security.JwtAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.bug_tracker.constants.SecurityConstants.ID;
import static com.bug_tracker.constants.SecurityConstants.ROLE;
import static java.lang.String.valueOf;

@Component
public class JwtTokenService {

    @Value("${jwt.signing.key}")
    private String signingKey;

    public String createJwtToken(User user) {
        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        Map<String, Object> claims = getClaims(user);
        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
        return jwt;
    }


    private Map<String, Object> getClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        String authorities = user.getRole().getRoleName();
        //  user.getRole().forEach(authority -> authoritiesssss.add(authority.getAuthority()));
        claims.put(ID, Long.toString(user.getId()));
        claims.put(ROLE, authorities);
        System.out.println(claims);
        return claims;
    }

    public JwtAuthentication parseToken(String jwt) {
        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return createAuthentication(claims);
    }

    private JwtAuthentication createAuthentication(Claims claims) {
        System.out.println(claims);
        long id = Long.parseLong(valueOf(claims.get(ID)));
        String authorities = valueOf(claims.get(ROLE));
        return new JwtAuthentication(id, authorities);
    }

}
