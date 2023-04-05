package com.example.spring.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${time}")
    private String value;
    private static final String SECRET_KEY = "367639792442264529482B4D6251655468576D5A7134743777217A25432A462D";

    public String extractEmail(String jwt){
        return extractClaim(jwt, Claims::getSubject);
    }

    // note ???
    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String jwt){
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .parseClaimsJws(jwt)
                .getBody();
    }

    public Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails){
        String email = extractEmail(jwt);
        return (email.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt){
        return extractExpiration(jwt).before(new Date());
    }

    public Date extractExpiration(String jwt){
        return extractClaim(jwt, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 60 * 1000 *Integer.parseInt(value)))
                .signWith(SignatureAlgorithm.HS256, getSignInKey())
                .compact();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

}
