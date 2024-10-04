package com.tom.nhl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tom.nhl.entity.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {
	
	Optional<AppRole> findByName(String name);
	List<AppRole> findAll();
}
