package com.tom.nhl.service;

import org.springframework.stereotype.Service;

import com.tom.nhl.dto.SeasonManagerDTO;
import com.tom.nhl.repository.GameRepository;

@Service
public class GameService {
	
	private final GameRepository gameRepo;
	
	public GameService(GameRepository gameRepo) {
		this.gameRepo = gameRepo;
	}
	
	public SeasonManagerDTO getSeasonManager() {
		return new SeasonManagerDTO(gameRepo.findAllSeasons());
	}

}
