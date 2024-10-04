package com.tom.nhl.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginFormDTO {

	@NotEmpty(message = "Username is required")
	@Size(min = 4, max = 25, message = "Username must be between 4 to 25 characters")
	private String username;
	
	@NotEmpty(message = "Password is required")
	@Size(min = 4, message = "Password must be at least 4 characters long")
	@Size(max = 20, message = "Password is too long (20 character max)")
	private String password;
	
	public LoginFormDTO() {
		super();
	}
	
	public LoginFormDTO(String username, String password) {
		this.username = username;
		this.password = password;
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
}
