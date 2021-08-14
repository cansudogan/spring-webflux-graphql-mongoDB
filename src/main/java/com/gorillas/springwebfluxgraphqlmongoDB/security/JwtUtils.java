package com.gorillas.springwebfluxgraphqlmongoDB.security;

import com.gorillas.springwebfluxgraphqlmongoDB.entity.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${gorillas.app.jwtSecret}")
    private String jwtSecret;

    @Value("${gorillas.app.jwtExpirationMs}")
    private int jwtExpirationMs;


    public String generateJwtToken(UserDto userDto) {
        return Jwts.builder()
                .setSubject((userDto.getUserName()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}