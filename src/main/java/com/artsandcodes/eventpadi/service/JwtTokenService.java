package com.artsandcodes.eventpadi.service;


import com.artsandcodes.eventpadi.security.UserDetail;
import com.artsandcodes.eventpadi.security.jwt.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by prestige on 1/15/2018.
 */
@Service
public class JwtTokenService {

    JwtConfig jwtConfig;

    @Autowired
    UserService userService;

    public JwtTokenService() {
        this.jwtConfig = new JwtConfig();
    }

    public Map generateToken(UserDetail userDetail){

        String accessToken = Jwts.builder()
                .setSubject(userDetail.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + this.jwtConfig.getTokenExpireTimeInMilli()))
                .signWith(SignatureAlgorithm.HS512,this.jwtConfig.getSecret())
                .compact();
        String refreshToken = Jwts.builder()
                .setSubject(userDetail.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + this.jwtConfig.getTokenExpireTimeInMilli()))
                .signWith(SignatureAlgorithm.HS512,this.jwtConfig.getSecret())
                .compact();


        Map map = new HashMap();
        map.put("accessToken",accessToken);
        map.put("refreshToken",refreshToken);

        return map;
    }

    public UsernamePasswordAuthenticationToken verifyToken(String token){

        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        Date expirationDate = claims.getExpiration();

        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        }

        return null;

    }
}
