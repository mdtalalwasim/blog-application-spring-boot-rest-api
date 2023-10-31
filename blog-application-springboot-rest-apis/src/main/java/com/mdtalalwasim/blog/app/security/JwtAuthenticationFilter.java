package com.mdtalalwasim.blog.app.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//add @Component because we can use this by @Autowired.
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	//this doFilterInternal method will call when Hit API Request
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//get jwt token from HttpServletRequest request
		String requestToken = request.getHeader("Authorization");
		
		//token starts with "Bearer 2005356251dfdferfd" --- token sample
		System.out.println("Token is : "+requestToken);
		
		//get username and actual token from the above token.
		String username =null;
		String token = null;
		
		if(requestToken!= null && requestToken.startsWith("Bearer")) {
			
			//actual token form jwt token
			token = requestToken.substring(7);//token without Bearer
			
			try {
				// username from jwt token
				username = this.jwtTokenHelper.getUsernameFromJWTToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token! ");
			} 
			catch (ExpiredJwtException e) {
				System.out.println("Your JWT Token is Expired!");
			} catch (MalformedJwtException e) {
				System.out.println("Invalid JWT Token!");
			}
			
			
			
			
			
		}else {
			System.out.println("JWT Token doesn't begin with Bearer! ");
		}
		
		
		//token is get then validate the token
		//validate jwt token
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails)){
				//if true
				//then do authentication / set authentication
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
				
			}else {
				System.out.println("Invalid JWT Token!");
			}
			
			
		}else {
			System.out.println("username is null and context is not null");
		}
		
		
		//request filter...
		filterChain.doFilter(request, response);
		

	}
	

}
