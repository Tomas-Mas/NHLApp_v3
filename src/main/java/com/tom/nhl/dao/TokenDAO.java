package com.tom.nhl.dao;

import java.util.Optional;

import com.tom.nhl.entity.Token;

public interface TokenDAO {

	Optional<Token> findByToken(String token);
}
