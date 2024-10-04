package com.tom.nhl.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "APP_ROLES")
public class AppRole {

	@Id
	@Column(name = "r_id")
	private Integer id;
	
	@Column(name = "r_name", unique = true)
	private String name;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<AppUser> users;
	
	public AppRole() {
		super();
	}
	
	public AppRole(AppRoleBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
	}
	
	public AppRole(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AppUser> getUsers() {
		return users;
	}

	public void setUsers(List<AppUser> users) {
		this.users = users;
	}
	
	public static AppRoleBuilder builder() {
		return AppRoleBuilder.getBuilder();
	}
	
	public static class AppRoleBuilder {
		private int id;
		private String name;
		
		public AppRoleBuilder() {
			super();
		}
		
		public static AppRoleBuilder getBuilder() {
			return new AppRoleBuilder();
		}
		
		public AppRoleBuilder id(int id) {
			this.id = id;
			return this;
		}
		
		public AppRoleBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public AppRole build() {
			return new AppRole(this);
		}
	}
}
