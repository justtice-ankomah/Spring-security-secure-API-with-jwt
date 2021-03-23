package com.example.demo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//Create a class to extends OncePerRequestFilter that will intercept every url once
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtUtility jwtutility;
	
	@Autowired
	MyuserDetailService userDetailService;

	//override it super-class doFilterInternal Method
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// get the jwt token from the headers => Berear eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdX
		final String authorizationHeader = request.getHeader("Authorization");
		// define two null variables
		String userName=null;
		String jwtToken =null;
		
		// check if the jwt token is not null and it start with Berear
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
			// cut the Berear + space from the token to get the exact token= eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdX
			jwtToken = authorizationHeader.substring(7);  
			//Jwt actually generate token with user details, so you can actually decode the token to get the userdetails. So..
			// retrieve the username from the above token by passing it to jwt getUsenameFromToken(token) method to become = justice
			userName = jwtutility.getUsernameFromToken(jwtToken);
		}
		
		//if username is not null and there is no value stored in security context
		if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		   //get the authenticated user details from userDetailService
			UserDetails userDetails = userDetailService.loadUserByUsername(userName);
			//validate the user token with the userDetails to see if is correct
			if(jwtutility.validateToken(jwtToken, userDetails)) { 
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// Add the Authenticated userDetails to the security-context
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	
	}

}
