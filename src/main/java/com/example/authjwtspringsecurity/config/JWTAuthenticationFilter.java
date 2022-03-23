package com.example.authjwtspringsecurity.config;


import com.example.authjwtspringsecurity.entities.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {

        super();
        this.authenticationManager = authenticationManager;
    }

    @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        AppUser appUser=null;

        try {
            appUser=new ObjectMapper().readValue(request.getInputStream(),AppUser.class);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
        System.out.println("*************");
        System.out.println("username : "+appUser.getUsername());
        System.out.println("pass : "+appUser.getUsername());
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(),appUser.getPassword()));
  }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,

                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User springuser = (User) authResult.getPrincipal();
        String jwtToken = Jwts.builder()
                .setSubject(springuser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET)
                 .claim("roles",springuser.getAuthorities())
                .compact();
        response.setContentType(APPLICATION_JSON_VALUE);
        response.addHeader(SecurityConstants.HEADER_STRING, jwtToken);
       // new ObjectMapper().writeValue(response.getOutputStream(),jwtToken);

    }
}