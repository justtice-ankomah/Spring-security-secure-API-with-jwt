package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data     //generate getters, setters and toString methods for all the class properties
//@NoArgsConstructor // generate a constructor for this class with no parameter
//@AllArgsConstructor // generate a constructor with a parameter of each properites of this class
public class JwtResponse {
	private String jwtToken;
	private String error;
	
	public JwtResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	JwtResponse(){
		
	}
	
	@Override
	public String toString() {
		return "JwtResponse [jwtToken=" + jwtToken + "]";
	}


	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
}
