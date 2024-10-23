package com.tom.nhl.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import com.tom.nhl.dto.LoginFormDTO;
import com.tom.nhl.dto.RegistrationFormDTO;
import com.tom.nhl.entity.AppRole;
import com.tom.nhl.entity.AppUser;
import com.tom.nhl.entity.Token;
import com.tom.nhl.repository.AppRoleRepository;
import com.tom.nhl.repository.AppUserRepository;
import com.tom.nhl.repository.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthenticationService {
	
	private final String guestName;
	private final String guestPassword;
	
	private final AppRoleRepository roleRepo;
	private final AppUserRepository userRepo;
	private final TokenRepository tokenRepo;
	
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authManager;
	
	public AuthenticationService(
			@Value("${application.security.guest-credentials.username}") String guestName,
			@Value("${application.security.guest-credentials.password}") String guestPassword,
			AppRoleRepository appRoleRepository, 
			AppUserRepository appUserRepository,
			TokenRepository tokenRepository,
			PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager) {
		this.guestName = guestName;
		this.guestPassword = guestPassword;
		this.roleRepo = appRoleRepository;
		this.userRepo = appUserRepository;
		this.tokenRepo = tokenRepository;
		this.passwordEncoder = passwordEncoder;
		this.authManager = authenticationManager;
	}
	
	public void registerUser(RegistrationFormDTO registrationForm) {
		AppRole role = roleRepo.findByName("USER")
				.orElseThrow(() -> new IllegalStateException("role USER was not initialized"));
		AppUser user = AppUser.builder()
				.firstName(registrationForm.getFirstname())
				.lastName(registrationForm.getLastname())
				.username(registrationForm.getUsername())
				.password(passwordEncoder.encode(registrationForm.getPassword()))
				.locked(false)
				.enabled(false)
				.role(role)
				.build();
		userRepo.save(user);
		String validationToken = createValidationToken(user);
		
		//auto validate user right after registering just to test things out
		System.out.println("generated token for user is: " + validationToken + " and user is auto validated");
		activateUser(validationToken);
	}
	
	public void loginUser(LoginFormDTO loginForm, HttpServletRequest request, HttpSession session) {
		Authentication authentication = authManager.authenticate(
				UsernamePasswordAuthenticationToken.unauthenticated(
						loginForm.getUsername(), 
						loginForm.getPassword())
		);
		
		setSecurityContextAndSession(authentication, request, session);
	}
	
	public void loginGuest(HttpServletRequest request, HttpSession session) {
		Authentication authentication = null;
		try {
			authentication = authManager.authenticate(
					UsernamePasswordAuthenticationToken.unauthenticated(guestName, guestPassword));
		} catch (BadCredentialsException e) {
			createGuestUser();
			authentication = authManager.authenticate(
					UsernamePasswordAuthenticationToken.unauthenticated(guestName, guestPassword));
		} finally {
			setSecurityContextAndSession(authentication, request, session);
		}
	}
	
	public void activateUser(String token) {
		Token storedToken = tokenRepo.findByToken(token)
				.orElseThrow(() -> new RuntimeException("Invalid token"));
		
		if(LocalDateTime.now().isAfter(storedToken.getExpiresAt())) {
			createValidationToken(storedToken.getUser());
			throw new RuntimeException("Activation token has expired, a new one was created, so use the new one and validate again");
		}
		
		AppUser user = storedToken.getUser();
		user.setEnabled(true);
		userRepo.save(user);
		
		storedToken.setValidatedAt(LocalDateTime.now());
		tokenRepo.save(storedToken);
	}
	
	private String createValidationToken(AppUser user) {
		String generatedToken = generateValidationToken(4);
		Token token = Token.builder()
				.token(generatedToken)
				.createdAt(LocalDateTime.now())
				.expiresAt(LocalDateTime.now().plusMinutes(10))
				.user(user)
				.build();
		tokenRepo.save(token);
		return generatedToken;
	}
	
	private String generateValidationToken(int length) {
		String chars = "0123456789ABCDEF";
		StringBuilder tokenBuilder = new StringBuilder();
		SecureRandom random = new SecureRandom();
		for(int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(chars.length());
			tokenBuilder.append(chars.charAt(randomIndex));
		}
		return tokenBuilder.toString();
	}
	
	private void createGuestUser() {
		AppRole role = roleRepo.findByName("GUEST")
				.orElseThrow(() -> new IllegalStateException("role GUEST was not initialized"));
		AppUser guest = AppUser.builder()
				.username(guestName)
				.password(passwordEncoder.encode(guestPassword))
				.firstName("Guest")
				.locked(false)
				.enabled(true)
				.role(role)
				.build();
		userRepo.save(guest);
		return;
	}
	
	private void setSecurityContextAndSession(Authentication authentication, HttpServletRequest request, HttpSession session) {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		
		SecurityContextHolderStrategy contextStrategy = SecurityContextHolder.getContextHolderStrategy();
		contextStrategy.setContext(context);
		
		if(session != null)
			session.invalidate();
		
		HttpSession newSession = request.getSession();
		newSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
	}

}
