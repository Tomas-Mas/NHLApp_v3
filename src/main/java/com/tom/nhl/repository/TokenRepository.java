package com.tom.nhl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tom.nhl.dao.TokenDAO;
import com.tom.nhl.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Integer>, TokenDAO {

}
