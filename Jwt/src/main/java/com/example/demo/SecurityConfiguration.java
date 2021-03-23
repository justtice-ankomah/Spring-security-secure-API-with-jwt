package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	MyuserDetailService  myuserDetailService;
	
	@Autowired
	JwtRequestFilter jwtRequestfilter;

	//authenticate the user details
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myuserDetailService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()   //disable cross-site-request-forgery becuase it enable by spring-security by default
		.authorizeRequests()    //authorize below request base on..
	    .antMatchers("/authenticate").permitAll()  //allow all users to access /authenticate url (becuase here is a login page to generate jwt-token)
		.anyRequest().authenticated()  //authenticate any other request to check if they provide the right jwt token in the headers	
		/* Spring security create sesssions by defualt to remember every user after successful authentication.
		 Tell spring not to create sessions.
		 */
	    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);	
		// call "JwtRequestfilter" first before calling "UsernamePasswordAuthenticationFilter"
		http.addFilterBefore(jwtRequestfilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
	// Password encoder To verify if user password is what is stored in db
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	/* There used To Be a Bean of authenticationManger in old version of Spring-security but it does not exist anymore
	  So you just need to override the "authenticationManager()" method of WebsecurityAdapter and add @Bean annotation to it
	*/
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	

}
