package com.tracknote;

import com.tracknote.dao.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Jwtutil {

    @Value("${jwt.secret}")
    private String secret;

    private UserRepository userRepository;

    public Jwtutil(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public String generateToken(String username){
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }


    public Boolean authenticateToken(String authHeader,UserDetails userDetails){
        final String username=extractUsername(authHeader);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(authHeader);
    }


    public String extractUsername(String authHeader){
        String token=authHeader.startsWith("Bearer")?authHeader.substring(7):authHeader;
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Boolean isTokenExpired(String authHeader){
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(authHeader).getBody().getExpiration().before(new Date());

    }

}
