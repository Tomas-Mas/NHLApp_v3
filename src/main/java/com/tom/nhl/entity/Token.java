package com.tom.nhl.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TOKENS")
public class Token {

	@Id
	@Column(name = "t_id")
	@SequenceGenerator(name = "tokenIdGenerator", sequenceName = "SEQ_TOKENS_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tokenIdGenerator")
	private Integer id;
	
	@Basic
	private String token;
	
	@Column(name = "created")
	private LocalDateTime createdAt;
	
	@Column(name = "expires")
	private LocalDateTime expiresAt;
	
	@Column(name = "validated")
	private LocalDateTime validatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private AppUser user;
	
	public Token() {
		super();
	}

	public Token(Integer id, String token, LocalDateTime createdAt, LocalDateTime expiresAt, LocalDateTime validatedAt,
			AppUser user) {
		super();
		this.id = id;
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.validatedAt = validatedAt;
		this.user = user;
	}
	
	public Token(TokenBuilder builder) {
		this.id = builder.id;
		this.token = builder.token;
		this.createdAt = builder.createdAt;
		this.expiresAt = builder.expiresAt;
		this.validatedAt = builder.validatedAt;
		this.user = builder.user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public LocalDateTime getValidatedAt() {
		return validatedAt;
	}

	public void setValidatedAt(LocalDateTime validatedAt) {
		this.validatedAt = validatedAt;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}
	
	public static TokenBuilder builder() {
		return TokenBuilder.getBuilder();
	}
	
	public static class TokenBuilder {
		private Integer id;
		private String token;
		private LocalDateTime createdAt;
		private LocalDateTime expiresAt;
		private LocalDateTime validatedAt;
		private AppUser user;
		
		public TokenBuilder() {
			super();
		}
		
		public static TokenBuilder getBuilder() {
			return new TokenBuilder();
		}
		
		public TokenBuilder id(Integer id) {
			this.id = id;
			return this;
		}
		
		public TokenBuilder token(String token) {
			this.token = token;
			return this;
		}
		
		public TokenBuilder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}
		
		public TokenBuilder expiresAt(LocalDateTime expiresAt) {
			this.expiresAt = expiresAt;
			return this;
		}
		
		public TokenBuilder user(AppUser user) {
			this.user = user;
			return this;
		}
		
		public Token build() {
			return new Token(this);
		}
	}
}
