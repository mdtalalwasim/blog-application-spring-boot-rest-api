package com.mdtalalwasim.blog.app.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenHelper {
	
	//jwt token validity in millisecond
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	
	private String secret = "jwtTokenWasimKey";
	
	//get username form jwt token
	public String getUsernameFromJWTToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	
	}
	
	// get expiration date form jwt token
	public Date getExpireDateFromJWTToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);

	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
		
	}
	
	//for getting any information from token we need the secret key...mind it.
	
	private Claims getAllClaimsFromToken(String token){
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	//check whether token is expired or not!
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpireDateFromJWTToken(token);
		return expiration.before(new Date());
	}
	
	//genereate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return generateToken(claims,userDetails.getUsername());
		
	}
	
	
	//
	private String generateToken(Map<String, Object> claims, String subject) {
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY * 100))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		
	}
	
	//validate token
	public Boolean validateToken(String token, UserDetails userDetails){
		final String username = getUsernameFromJWTToken(token);
		return ( username.equals(userDetails.getUsername()) && !isTokenExpired(token) );
	}
	
	
	
	

}
