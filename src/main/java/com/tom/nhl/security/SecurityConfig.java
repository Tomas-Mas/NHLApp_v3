package com.tom.nhl.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.tom.nhl.service.AuthenticationService;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	
	//private final JwtFilter jwtFilter;
	private final AuthenticationService authService;
	
	public SecurityConfig(/*JwtFilter jwtFilter, */
			AuthenticationService authService) {
		//this.jwtFilter = jwtFilter;
		this.authService = authService;
		
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		String[] adminPatterns = {"/admin/**"};
		
		http
				.cors(Customizer.withDefaults())
				.csrf(csrf -> csrf
						.csrfTokenRepository(new HttpSessionCsrfTokenRepository()))
				.authorizeHttpRequests(req -> req
						.requestMatchers(adminPatterns).hasAuthority("ADMIN")
						.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
						.anyRequest().authenticated()
				)
				.formLogin(form -> form.loginPage("/auth/login"))
				.logout(logout -> logout
						.logoutUrl("/auth/logout")
						.logoutSuccessUrl("/nhl/home"))
				.sessionManagement(session -> session.maximumSessions(1))
				//.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
				.addFilterAfter(new GuestUserFilter(authService), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
