package com.tom.nhl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.nhl.dao.GameDAO;
import com.tom.nhl.entity.Game;

@Repository
public interface GameRepository extends GameDAO, JpaRepository<Game, Integer> {

	public List<Integer> findAllSeasons();
}
