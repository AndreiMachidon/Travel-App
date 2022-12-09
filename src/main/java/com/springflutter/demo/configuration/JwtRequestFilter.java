package com.springflutter.demo.configuration;

import com.springflutter.demo.service.JwtService;
import com.springflutter.demo.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * This class checks the jwt token for every request to the server
 */
@Component

public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

      final String header = request.getHeader("Authorization"); //we take the jwt from the request header

        String jwtToken = null;
        String username = null;

      if(header != null && header.startsWith("Bearer ")){
          jwtToken = header.substring(7); //we cut only the jwt, with no 'Bearer'


          try{
            username = jwtUtil.getUsernameFromToken(jwtToken); //we take the username from the jwt claims
          }catch (IllegalArgumentException e){
              System.out.println("Unable to get Jwt token");
          }catch (ExpiredJwtException e){
              System.out.println("Jwt token is expired");
          }
      }

      else{
          System.out.println("Jwt token does not start with Bearer");
      }


      //if we find a user by username we call this method
      if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
          UserDetails userDetails = jwtService.loadUserByUsername(username);

            //if the token is valid, we create an authentication token with user details, and it's authorities
          if(jwtUtil.validateToken(jwtToken, userDetails)){
              UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                      null,
                      userDetails.getAuthorities());

              usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

              SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          }
      }

        filterChain.doFilter(request, response);


    }
}
