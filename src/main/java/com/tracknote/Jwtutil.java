package com.tracknote;

import com.tracknote.dao.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


@Component//tells Spring to automatically detect this class and create an instance of it as a singleton bean in the Spring application context during component scanning.
public class Jwtutil {

    @Value("${jwt.secret}")
    private String secret;


    public String generateToken(String username){
        //Since the key I have in app prop is base 64 encoded 256 bits key. We need to decode
        Key key= Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        /*This decodes the secret string, which is in Base64 format, into a byte array.
Why: Because your secret is stored as a Base64-encoded string, and cryptographic functions work with raw bytes, not strings.

JWT is stateless; server does not need to store session info.
The secret key is used to sign the token (with HMAC or other algorithms).
The client must present the token on every request for authorization.
Tokens typically have expiry to enhance security.
JWT improves scalability because no server-side session storage is needed. JWT is primarily about authorization*/

        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean authenticateToken(String token,UserDetails userDetails){
        final String username=extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String extractUsername(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Boolean isTokenExpired(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration().before(new Date());

    }

}
