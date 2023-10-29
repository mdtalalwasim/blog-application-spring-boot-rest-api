package com.mdtalalwasim.blog.app.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		// this will send error msg, 
		//when an unauthorized user want to access authorized resources/apis.
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied !!!");

	}
	
	

}
