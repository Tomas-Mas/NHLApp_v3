package com.tom.nhl.entity;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tom.nhl.enums.AppRoleType;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "APP_USERS")
@EntityListeners(AuditingEntityListener.class)
public class AppUser implements UserDetails, Principal {
	private static final long serialVersionUID = -3248477394884628205L;
	
	@Id
	@Column(name = "u_id")
	@SequenceGenerator(name = "userIdGenerator", sequenceName = "SEQ_APPUSERS_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
	private Integer id;
	
	@Column(unique = true)
	private String username;
	
	@Column(name = "u_password")
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "birth_date")
	private LocalDate dateOfBirth;
	
	@Column(name = "u_locked")
	private boolean locked;
	
	@Basic
	private boolean enabled;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private AppRole role;
	
	@CreatedDate
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@Column(name = "last_modified_date", insertable = false)
	private LocalDateTime lastModifiedDate;
	
	public AppUser() {
		super();
	}

	public AppUser(Integer id, String username, String password, String firstName, String lastName,
			LocalDate dateOfBirth, boolean locked, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.locked = locked;
		this.enabled = enabled;
	}
	
	public AppUser(AppUserBuilder builder) {
		this.username = builder.username;
		this.password = builder.password;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.locked = builder.locked;
		this.enabled = builder.enabled;
		this.role = builder.role;
	}
	
	@Override
	public String getName() {
		return username;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AppRoleType.valueOf(role.getName()).getAuthorities();
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public static AppUserBuilder builder() {
		return AppUserBuilder.getBuilder();
	}
	
	public static class AppUserBuilder {
		private String username;
		private String password;
		private String firstName;
		private String lastName;
		private boolean locked;
		private boolean enabled;
		private AppRole role;
		
		public AppUserBuilder() {
			super();
		}
		
		public static AppUserBuilder getBuilder() {
			return new AppUserBuilder();
		}
		
		public AppUserBuilder username(String username) {
			this.username = username;
			return this;
		}
		
		public AppUserBuilder password(String password) {
			this.password = password;
			return this;
		}
		
		public AppUserBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public AppUserBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public AppUserBuilder locked(boolean locked) {
			this.locked = locked;
			return this;
		}
		
		public AppUserBuilder enabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}
		
		public AppUserBuilder role(AppRole role) {
			this.role = role;
			return this;
		}
		
		public AppUser build() {
			return new AppUser(this);
		}
	}
}
