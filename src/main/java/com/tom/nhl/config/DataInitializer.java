package com.tom.nhl.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tom.nhl.entity.AppRole;
import com.tom.nhl.entity.AppUser;
import com.tom.nhl.repository.AppRoleRepository;
import com.tom.nhl.repository.AppUserRepository;

@Configuration
public class DataInitializer {

	@Bean
	public CommandLineRunner initialize(
			AppRoleRepository roleRepo, 
			AppUserRepository userRepo,
			PasswordEncoder passwordEncoder
	) {
		return args -> {
			if(userRepo.findByUsername("Admin").isEmpty()) {
				AppRole adminRole = roleRepo.findByName("ADMIN")
						.orElseThrow(() -> new IllegalStateException("role ADMIN was not initialized"));
				
				userRepo.save(AppUser.builder()
						.username("Admin")
						.password(passwordEncoder.encode("password"))
						.locked(false)
						.enabled(true)
						.role(adminRole)
						.build()
				);
			}
		};
	}
}
