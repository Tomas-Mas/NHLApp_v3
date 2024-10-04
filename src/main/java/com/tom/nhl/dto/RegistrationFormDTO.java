package com.tom.nhl.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegistrationFormDTO {

	@NotEmpty(message = "Username is required")
	@Size(min = 4, max = 25, message = "Username must be between 4 to 25 characters")
	private String username;
	
	@NotEmpty(message = "First name is required")
	@Size(min = 4, max = 20, message = "First name must be between 4 to 20 characters")
	private String firstname;
	
	@NotEmpty(message = "Last name is required")
	@Size(min = 4, max = 20, message = "Last name must be between 4 to 20 characters")
	private String lastname;
	
	@NotEmpty(message = "Password is required")
	@Size(min = 4, message = "Password must be at least 4 characters long")
	@Size(max = 20, message = "Password is too long (20 character max)")
	private String password;
	
	public RegistrationFormDTO() {
		super();
	}
	
	public RegistrationFormDTO(String username, String firstname, String lastname, String password) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
