package com.mdtalalwasim.blog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdtalalwasim.blog.app.payloads.JwtAuthenticationRequest;
import com.mdtalalwasim.blog.app.payloads.JwtAuthenticationResponse;
import com.mdtalalwasim.blog.app.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthRestController {
	
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> createToken(@RequestBody JwtAuthenticationRequest request){
		
		//authenticate method body define below
		this.authenticate(request.getUsername(), request.getPassword());
		//if authentication going well, then we need to generate our token.
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String ourGeneratedToken = this.jwtTokenHelper.generateToken(userDetails);//generateToken takes userDetails
		
		JwtAuthenticationResponse response = new JwtAuthenticationResponse();
		response.setToken(ourGeneratedToken);
		
		
		
		return new ResponseEntity<JwtAuthenticationResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) {
		
		//need to authenticate
		//authenticationManager can authenticate
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		//authenticate the username and password
		this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		//if authentication going well, then we need to generate our token.
		//if not going well, then it will generate exception... we will handle it globally
		
		
		
	}
}
