package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Users {

	@Id
	@GeneratedValue
	private Integer id;
	private String userName;
	private String userPassword;
	
	// To string methods for the properties
	@Override
	public String toString() {
		return "Users [userName=" + userName + ", userPassword=" + userPassword + ", id=" + id + "]";
	}

	
	//Geters and setters for the above properties

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
