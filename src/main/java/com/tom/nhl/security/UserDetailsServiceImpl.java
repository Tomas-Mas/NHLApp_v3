package com.tom.nhl.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tom.nhl.entity.AppUser;
import com.tom.nhl.repository.AppUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final AppUserRepository userRepo;
	
	public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
		this.userRepo = appUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepo.findUserWithRoleByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User was not found!"));
		
		if(!user.isEnabled() || !user.isAccountNonLocked() || !user.isAccountNonExpired() || !user.isCredentialsNonExpired()) {
			throw new UsernameNotFoundException("there is some problem with user profile (unauthenticated, locked or expired");
		}
		
		UserDetails userDetails = User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.authorities(user.getAuthorities())
				.accountExpired(!user.isAccountNonExpired())
				.accountLocked(user.isLocked())
				.credentialsExpired(!user.isCredentialsNonExpired())
				.disabled(!user.isEnabled())
				.build();
		return userDetails;
	}

}
