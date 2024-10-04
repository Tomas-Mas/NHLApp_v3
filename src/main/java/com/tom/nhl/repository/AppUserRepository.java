package com.tom.nhl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tom.nhl.dao.AppUserDAO;
import com.tom.nhl.entity.AppUser;

public interface AppUserRepository extends AppUserDAO, JpaRepository<AppUser, Integer> {
	
	Optional<AppUser> findByUsername(String username);
}
