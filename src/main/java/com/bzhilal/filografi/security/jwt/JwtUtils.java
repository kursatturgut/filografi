package com.bzhilal.filografi.security.jwt;


import com.bzhilal.filografi.security.service.UserDetailsImplement;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

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

    public Long getIdFromJwtToken(String token){
        return Long.parseLong(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject());
    }

    public boolean validateJwtToken(String authToken) {  // tokenla devam ederken hata olursa
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }
        catch (SecurityException e) {  // algoritma ekledik sorun varsa
            logger.error("Invalid JWT signature: {}", e.getMessage());
        }
        catch (MalformedJwtException e) { // token  hatalıysa dönecek
            logger.error("Invalid JWT token: {}", e.getMessage());
        }
        catch (ExpiredJwtException e) { // zaamnı dolduysa bu hata dönecek
            logger.error("JWT token is expired: {}", e.getMessage());
        }
        catch (UnsupportedJwtException e) { // desteklenmeyn token hatsı mesaj dönecek
            logger.error("JWT token is unsupported: {}", e.getMessage());
        }
        catch (IllegalArgumentException e) { // token eklenmediyse bu hata dönecek
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }




}
