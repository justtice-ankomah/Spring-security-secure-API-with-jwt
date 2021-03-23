package com.example.demo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyuserDetailService implements UserDetailsService{


	@Autowired
	MyRepository myRepository;
	
	// Below String username in the parameter ("username" could be justice, which will be passed in html input from the client)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		// select * from Users where userName=Justice
		Optional<Users> user = myRepository.findByuserName(username);
	
		/* Throw an exception if no user with name "justice" is found in the database
		  If no error, the "u" below will contain userDetails*/
      Users u =user.orElseThrow(()-> new UsernameNotFoundException("user not found"));
      
      /*
 	 The above method am in "loadUserByUsername" returns "UserDetails" So i need to return a class that implements UserDetails interface 
 	 with all the user details (so am creating "SecurityUser" class to implements it)
 	 */
	
	return new SecurityUser(u);
	
	
	/* above return is returning bellow details to "auth.userDetailsService(myuserDetailService);" in SecurityConfiguration
	
 userName= "justice"      // this is coming from Users class
 password= "justice12455" // this is coming from Users class
 isAccountNonExpired=true
 isAccountNonLocked=true
 isCredentialsNonExpired=true
 isEnabled= true
 getAuthorities= list of authorities
 */
		

	
	}
	
	

}
