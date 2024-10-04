package com.tom.nhl.dao;

import java.util.Optional;

import com.tom.nhl.entity.AppUser;

public interface AppUserDAO {

	Optional<AppUser> findUserWithRoleByUsername(String username);
}
