package com.Ecommerce.User.Util;

import com.Ecommerce.User.Entity.Auth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey123456";


    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    // Generate JWT Token
    public String generateToken(Auth auth) {

        return Jwts.builder()
                .setSubject(auth.getEmail())
                .claim("role", auth.getRole().name())
                .claim("userId",auth.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }


//    // Extract Email from Token
//    public String extractEmail(String token) {
//
//        return getClaims(token).getSubject();
//    }
//
//
//    // Extract Role from Token
//    public String extractRole(String token) {
//
//        return getClaims(token)
//                .get("role", String.class);
//    }
//
//
//    // Validate Token
//    public boolean ValidateToken(String token) {
//
//        try {
//
//            Claims claims = getClaims(token);
//
//            return claims.getExpiration()
//                    .after(new Date());
//
//        } catch (Exception e) {
//
//            return false;
//        }
//    }
//
//
//    // Get Claims
//    private Claims getClaims(String token) {
//
//        return Jwts.parserBuilder()
//                .setSigningKey(getKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
}