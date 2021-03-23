package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	SecurityConfiguration securityConfiguration;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtility jwtUtility;
	
	@Autowired
	MyuserDetailService  myuserDetailService;
	
	
	@GetMapping("/home")
	public String home() {
		return "<h1> Welcome Home </h1>";
	}
	
	@GetMapping("/")
	public String index() {
		return "<h1> Index page </h1>";
	}
	
	
	/* When the user navigate to localhost:8082/authenticate
	 Bellow, all that you are doing is that, you are grabbing the user input and authenticating it to see if is equal to what is stored in db.
	 
	 If is equal, just generate a jwt-token and send it for him to access other urls. 
	 
	 If user input a wrong details, just sent him an error
	 
	 (so this localhost:8082/authenticate url start and end here, there won't be any filter to intercept it)
	 */
	
	//Post-Method To Generate jwt Token For The User (The User is passing his html input here)
	@PostMapping("/authenticate")
	// Assign the user input to jwtRequest Object
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest)  {
		
		/* Authenticate the user Details he sent from the html input to see if is correct first, Before you Generate a jwt token for him
		 By creating an Autowired object of "AuthenticationManager" interface and call it authentcate method. 
		 */
		 try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                    		jwtRequest.getUsername(),
	                    		jwtRequest.getPassword()
	                    )
	            ); //throw error if username and password not correct
	        } catch (BadCredentialsException e) {
	        	return ResponseEntity.ok(new JwtResponse("error Invalid user details"));
	        }
		
		 final UserDetails userDetails= myuserDetailService.loadUserByUsername(JwtRequest.getUsername());
		 final String token = jwtUtility.generateToken(userDetails);
		return ResponseEntity.ok( new JwtResponse(token));
		
	}
}
