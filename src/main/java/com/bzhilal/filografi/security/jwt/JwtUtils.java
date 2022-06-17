package com.bzhilal.filografi.security.jwt;


import com.bzhilal.filografi.security.service.UserDetailsImplement;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.Authentication;

import java.util.Date;

public class JwtUtils {

    @Value("${backendapi.app.jwtSecret}")
    private String jwtSecret;

    @Value("${backendapi.app.jwtExpirationMs}")
    private Long jwtExpirationMs;

    public  String generateJwtToken(Authentication authentication){

        UserDetailsImplement userDetailsImplement= (UserDetailsImplement) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(""+(userDetailsImplement.getId()))
                .setIssuedAt((new Date()))
                .setExpiration(new Date((new Date()).getTime()+ jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();

    }


}
