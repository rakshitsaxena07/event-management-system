package com.technogise.event_management_system.security;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;


@Service
public class JwtService {
    @Value("${application.security.jwt.secret}")  //injects the secret key from application properties
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

     private SecretKey getSigningKey() { //this method generates a cryptographic key from the secretKey string which is used to sign and verify JWT tokens
        return Keys.hmacShaKeyFor(secretKey.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    public String generateToken(String email) { //will return a JWT token as a string, and takes the user's email as input
         return Jwts.builder() //starts building new token
                    .subject(email)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                    .signWith(getSigningKey()) //signs the token with the generated key
                    .compact();
    }

     public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }
    
    public boolean isTokenValid(String token, String userEmail) {
        final String email = extractEmail(token);
        return (email.equals(userEmail)) && !isTokenExpired(token);
    }
    
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());//it compares expiry date with current date(before)
    }

     private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey()) // Fixed: Now it has access to the key
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}


//Example expalantion
/*secretKey = The restaurant's secret stamp that only staff can verify

jwtExpiration = "Valid for 24 hours" printed on the card

.subject(email) = Your name on the card

.issuedAt() = Today's date stamped on the card

.expiration() = Tomorrow's date (when it expires)

.signWith(key) = The restaurant's special hologram that can't be copied

.compact() = The physical card you receive
 */


//missing things 
/*
The main missing pieces were:

Exception handling (tokens can be invalid)
Token validation method
Logout support (token blacklisting)
Refresh token functionality

 */