package com.ecommerce.API_Gateway.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey123456";


    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Validate Token
    public boolean ValidateToken(String token) {

        try {

            Claims claims = getClaims(token);

            return claims.getExpiration()
                    .after(new Date());

        } catch (Exception e) {

            return false;
        }
    }

    // Get Claims
    private Claims getClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extract Email from Token
    public String extractEmail(String token) {

        return getClaims(token).getSubject();
    }


    // Extract Role from Token
    public String extractRole(String token) {

        return getClaims(token)
                .get("role", String.class);
    }

    // Extract userId from token
    public UUID extractUserId(String token)
    {
        String id=getClaims(token).get("userId",String.class);
        return UUID.fromString(id);
    }

}
