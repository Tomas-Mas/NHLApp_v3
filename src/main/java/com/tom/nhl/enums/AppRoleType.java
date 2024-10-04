package com.tom.nhl.enums;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum AppRoleType {

	GUEST("GUEST", List.of("GUEST")),
	USER("USER", List.of("USER", "GUEST")),
	ADMIN("ADMIN", List.of("ADMIN", "USER", "GUEST"));
	
	private String type;
	private List<String> roles;
	
	private AppRoleType(String type, List<String> roles) {
		this.type = type;
		this.roles = roles;
	}
	
	public AppRoleType valueOfType(String type) {
		for(AppRoleType role: values()) {
			if(role.type.equals(type))
				return role;
		}
		
		throw new IllegalStateException("something went wrong with AppRole mapping to AppRoleType enum");
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.toList();
	}
}
