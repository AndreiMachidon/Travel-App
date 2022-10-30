package com.springflutter.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "secretKey123";

    private static final int TOKEN_VALIDITY = 3600 * 5;

    //this method returns the username from the Jwt Token
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    //this method takes a token and apply it to a generic function
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    //this method takes the token and returns its claims(claims = payload = body = the information that are contained in jwt)
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //this method checks if the username from the token is equal to the username from userDetails
    public boolean validateToken(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); //we check if the username is equal and also if the token is expired
    }

    //we check if the expiration date is before the current date
    private boolean isTokenExpired(String token){
        final Date expirationDate= getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }
 //this method gets the expiration date from the token
    private Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //this method generates a jwt token
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }



}
