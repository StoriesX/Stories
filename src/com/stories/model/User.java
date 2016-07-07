package com.stories.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public User(String username){
		this.username = username;
	}
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String firstName, String lastName){
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static boolean validate(String un, String pw, String fn, String ln) {
		if(!isValidName(un)||!isValidName(fn)||!isValidName(ln)||!isValidPassword(pw)){
			return false;
		}
		return true;
	}

	private static boolean isValidPassword(String pw) {
		//add more later on
		return pw!=null && pw.trim().length() > 0;
	}

	private static boolean isValidName(String un) {
		return un != null && un.trim().length() > 0;
	}
}
