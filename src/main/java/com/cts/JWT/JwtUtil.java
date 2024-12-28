package com.cts.JWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
	
	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String extractUsername(String token){
        return extractClaims(token , Claims::getSubject);
    }
	
	//To extract the expiration time of the token
    public Date extractExpiration(String token){
        return extractClaims(token , Claims::getExpiration);
    }

	public <T> T extractClaims(String token,Function<Claims,T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

//if someone temper with jwt token then secret key is changed automatically thats why we always check the secret key
	public Claims extractAllClaims(String token) {
		//return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
	}
	 //checks if the token is expired or not
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String Username , String role){
        Map<String , Object> claims = new HashMap<>();
        claims.put("role" , role);
        return createtoken(claims, Username);
    }
    
    //subject==username
    private String createtoken(Map<String , Object> claims , String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //if we run the code again then previous token will not remain valid as the secret key is changed during the application restart.
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                //.signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
                .signWith(SECRET_KEY).compact();
    }
    
    //validating token (it checks if the username is valid means is it the same and also checks if the token is expired or active
    public Boolean validatetoken(String token , UserDetails userDetails){
        final String Username = extractUsername(token);
        return (Username.equals(userDetails.getUsername()) && !isTokenExpired(token) );
    }
}
