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

    /**
     *This method returns the token subject(in this case the username)
     * @param token the jwt token(String)
     * @return the subject from the claims(int this case it's the username)
     */
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     *This method signs the key with the secret key and gets all the claims from it
     * @param token this is the jwt token(string)
     * @return returns all the token claims(payload)
     */
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    /**
     *This method signs the key with the secret key and gets all the claims from it
     * @param token this is the jwt token(string)
     * @return returns all the token claims(payload)
     */
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    public boolean validateToken(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); //we check if the username is equal and also if the token is expired
    }


    private boolean isTokenExpired(String token){
        final Date expirationDate= getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    /**
     * This method gets the expiration date from token
     * @param token the jwt token
     * @return returns the expiration date
     */
    private Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * This method generates a jwt token
     * @param userDetails these are the user details which we put in the token subject
     * @return returns the jwt object(String)
     */
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
