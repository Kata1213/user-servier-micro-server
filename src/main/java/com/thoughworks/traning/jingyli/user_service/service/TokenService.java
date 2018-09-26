package com.thoughworks.traning.jingyli.user_service.service;


import com.thoughworks.traning.jingyli.user_service.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;


@Service
public class TokenService {
    public static final byte[] SECRET_KEY = "secret_key".getBytes();

    public String createToken(User user) {
        String signature = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .claim("user_id", user.getId())
                .compact();
        return signature;
    }

    public Long parseToken(String token) {
        System.out.println("!!!!!!!!!!!!!!In +" + token);
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("claims="+claims);
            System.out.println(claims.get("id",Long.class));


            return claims.get("id",Long.class);
        } catch (RuntimeException e) {
            return null;
        }

    }
}
