package com.backend.stoa.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Collection;
import java.util.Date;

@Service
public class JwtUtil {

    private static final String AUTHORITIES = "authorization";

    private int JWT_EXPIRATION_TIME = 100 * 60 * 60;

    private String JWT_SECRET = "oYdmMqiysWb11whE8rNa1Uz84hB66f5i";

    private Key KEY;

    JwtUtil(){
        KEY = new SecretKeySpec(JWT_SECRET.getBytes(), 0, JWT_SECRET.getBytes().length, "HmacSHA256");
    }

    public String createToken(UserDetails userDetails){
        String username = userDetails.getUsername();

        Collection<? extends GrantedAuthority> roles  = userDetails.getAuthorities();

        return Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES, roles)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean hasTokenExpired(String token){
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return (userDetails.getUsername().equals(username) && !hasTokenExpired(token));
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Collection<? extends  GrantedAuthority> getRoles(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJwt(token).getBody();
        return (Collection<? extends GrantedAuthority>) claims.get(AUTHORITIES);
    }
}
