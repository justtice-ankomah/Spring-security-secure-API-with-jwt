package com.example.demo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/*
So here is all what you are sending to the MyuserDetailSerVice as an object
 
 userName= "justice"      // this is coming from Users class
 password= "justice12455" // this is coming from Users class
 isAccountNonExpired=true
 isAccountNonLocked=true
 isCredentialsNonExpired=true
 isEnabled= true
 getAuthorities= list of authorities
 */

/* You need to return only the userName and password to "MyuserDetailSerVice" class. But Spring-security want to know some extra informations  
 like, is AccountNonExpired?, isAccounNonLocked? Because of that you need to implement userDetails interface and override those methods
 and specifty those informations. (Spring did this because, even if user information is correct and his account is locked or expired, 
 you don't have to allow him to perform any operation on the website.) Typically if you are developing a website that will require you to 
 lock, block or delete other users account. You will save a true or false column in your db to specify that such user is blocked or not. 
 so you can load those details from db and specify it here in is  isAccountNonLocked? and the rest
 */
public class SecurityUser implements UserDetails{
	
	public String userName;
	public String password;
	
	public SecurityUser(Users users){
		this.userName= users.getUserName();
		this.password= users.getUserPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(()->"read");
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	} 

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
