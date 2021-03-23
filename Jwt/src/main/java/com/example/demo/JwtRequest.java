package com.example.demo;

//make use to add the lombok dependency to your porm.xml
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data     //generate getters, setters and toString methods for all the class properties
//@NoArgsConstructor // generate a constructor for this class with no parameter
//@AllArgsConstructor // generate a constructor with a parameter of each properites of this class
public class JwtRequest {
	
	private static String username;
	private static String password;

	JwtRequest(){}
	
	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "JwtRequest [username=" + username + ", password=" + password + "]";
	}
	public static String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
