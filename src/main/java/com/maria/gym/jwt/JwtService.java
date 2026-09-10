package com.maria.gym.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {

    private final SecretKey key = Jwts.SIG.HS256.key().build();

    private Long expirationTime = 600000L;

    public String getToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    public Claims jwtParseClaims(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token){
        String username = jwtParseClaims(token).getSubject();
        return username;
    }

    public boolean tokenValid(String token, UserDetails userDetails){
        String username = getUsername(token);
        boolean valid = username.equals(userDetails.getUsername()) && jwtParseClaims(token).getExpiration().before(new Date());

        return valid;
    }
}















